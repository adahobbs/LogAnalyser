package com.monitoring.log.event;

import com.monitoring.log.database.Event;
import com.monitoring.log.event.model.LogEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Generates {@link Event}s
 */
public class EventGenerator {

    private static final Logger LOGGER = LogManager.getLogger(EventGenerator.class);

    private final EventDurationCalculator eventDurationCalculator;

    /**
     * Initialises an {@link EventGenerator}
     *
     * @param eventDurationCalculator The {@link EventDurationCalculator}
     */
    public EventGenerator(EventDurationCalculator eventDurationCalculator) {
        this.eventDurationCalculator = eventDurationCalculator;
    }

    /**
     * Generates an {@link Event}
     *
     * @param logEvent1 First {@link LogEvent}
     * @param logEvent2 Second {@link LogEvent}
     * @param threshold Threshold by which an event should be flagged if its duration is above it
     * @return An {@link Event}
     */
    public Event generate(LogEvent logEvent1, LogEvent logEvent2, long threshold) {
        long duration = this.eventDurationCalculator.calculate(logEvent1, logEvent2);

        LOGGER.debug("Generating event with id " + logEvent1.getId() + ". isAlert=" + (duration > threshold));
        // Flag if duration is above the provided threshold
        return new Event(logEvent1.getId(), duration, logEvent1.getType(), logEvent1.getHost(), duration > threshold);
    }

}