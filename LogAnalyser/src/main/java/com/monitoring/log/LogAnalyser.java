package com.monitoring.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitoring.log.database.Event;
import com.monitoring.log.database.EventRepository;
import com.monitoring.log.event.EventGenerator;
import com.monitoring.log.event.LogEventPairValidator;
import com.monitoring.log.event.model.LogEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Analyses logs
 */
public class LogAnalyser {

    private static final Logger LOGGER = LogManager.getLogger(LogAnalyser.class);

    private final ObjectMapper objectMapper;
    private final LogFileReader logFileReader;
    private final LogEventPairValidator logEventPairValidator;
    private final EventGenerator eventGenerator;
    private final EventRepository eventRepository;

    /**
     * Initialises a new {@link LogAnalyser}
     *
     * @param objectMapper          An {@link ObjectMapper}
     * @param logFileReader         A {@link LogFileReader}
     * @param logEventPairValidator An {@link LogEventPairValidator}
     * @param eventGenerator        An {@link EventGenerator}
     * @param eventRepository       An {@link EventRepository}
     */
    public LogAnalyser(ObjectMapper objectMapper, LogFileReader logFileReader,
                       LogEventPairValidator logEventPairValidator, EventGenerator eventGenerator,
                       EventRepository eventRepository) {
        this.objectMapper = objectMapper;
        this.logFileReader = logFileReader;
        this.logEventPairValidator = logEventPairValidator;
        this.eventGenerator = eventGenerator;
        this.eventRepository = eventRepository;
    }

    /**
     * Analyses a log file on {@code path} and inserts into the database. Generates events which may contain an alert
     * if the event duration is longer than {@code threshold}.
     *
     * @param path      Path of the log file
     * @param threshold Threshold by which an alert will be triggered if the event duration is above it
     */
    public void analyse(String path, long threshold) {
        // Events generated from processing log events
        List<Event> events = new ArrayList<>();

        // Map of log event id to log event
        Map<String, LogEvent> logEvents = new HashMap<>();

        // TODO - Concurrently analyse logs to speed analysis
        this.logFileReader.readLogFile(path)
                .forEach(logLine ->
                        generateLogEvent(logLine)
                                .flatMap(event -> analyse(event, threshold, logEvents)).ifPresent(events::add));

        // TODO - Optimise by inserting into the database in batches
        LOGGER.debug("Inserting the following events into the database: " + events);
        // Save into the database
        if (!events.isEmpty()) {
            this.eventRepository.saveAll(events);
        }
    }

    /**
     * Analyses the provided {@code logEvent} and generates an {@link Event} if appropriate
     *
     * @param logEvent  The {@link LogEvent}
     * @param threshold Threshold by which an alert will be triggered if the event duration is above it
     * @param logEvents Map containing previous {@link LogEvent} which have not been processed into an {@link Event}
     * @return An {@link Event} if the {@code logEvent} could be processed, otherwise an empty {@link Optional}
     */
    private Optional<Event> analyse(LogEvent logEvent, long threshold, Map<String, LogEvent> logEvents) {
        LOGGER.debug("Processing log event: " + logEvent);
        // Check if log event counterpart already analysed
        if (logEvents.containsKey(logEvent.getId())) {
            LogEvent logEvent2 = logEvents.get(logEvent.getId());

            // Ensure that the log events are a valid pair
            if (this.logEventPairValidator.arePair(logEvent, logEvent2)) {
                // Remove from log event map as now processed
                logEvents.remove(logEvent.getId());
                return Optional.of(this.eventGenerator.generate(logEvent, logEvent2, threshold));
            } else {
                LOGGER.warn("Invalid pair found with id " + logEvent.getId());
            }

        } else {
            // Put into log event map to be processed in future
            logEvents.put(logEvent.getId(), logEvent);
        }
        return Optional.empty();
    }

    /**
     * Generates a {@link LogEvent}
     *
     * @param logLine Log line
     * @return A {@link LogEvent}
     */
    private Optional<LogEvent> generateLogEvent(String logLine) {
        try {
            return Optional.of(this.objectMapper.readValue(logLine, LogEvent.class));
        } catch (JsonProcessingException e) {
            LOGGER.error("Issue while reading log line: " + logLine, e);
            return Optional.empty();
        }
    }

}