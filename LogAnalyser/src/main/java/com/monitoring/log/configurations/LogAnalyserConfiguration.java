package com.monitoring.log.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitoring.log.LogAnalyser;
import com.monitoring.log.LogFileReader;
import com.monitoring.log.database.EventRepository;
import com.monitoring.log.event.EventDurationCalculator;
import com.monitoring.log.event.EventGenerator;
import com.monitoring.log.event.LogEventPairValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Contains dependencies to analyse logs
 */
@Configuration
public class LogAnalyserConfiguration {

    /**
     * Initialises the {@link LogAnalyser}
     *
     * @param objectMapper          An {@link ObjectMapper}
     * @param logFileReader         A {@link LogFileReader}
     * @param logEventPairValidator An {@link LogEventPairValidator}
     * @param alertGenerator        An {@link EventGenerator}
     * @param eventRepository       An {@link EventRepository}
     * @return A {@link LogAnalyser}
     *//**/
    @Bean
    public LogAnalyser logAnalyser(ObjectMapper objectMapper, LogFileReader logFileReader,
                                   LogEventPairValidator logEventPairValidator, EventGenerator alertGenerator,
                                   EventRepository eventRepository) {
        return new LogAnalyser(objectMapper, logFileReader, logEventPairValidator, alertGenerator, eventRepository);
    }

    /**
     * Initialises the {@link ObjectMapper} dependency
     *
     * @return The {@link ObjectMapper} dependency
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Initialises the {@link LogFileReader} dependency
     *
     * @return The {@link LogFileReader} dependency
     */
    @Bean
    public LogFileReader logFileReader() {
        return new LogFileReader();
    }

    /**
     * Initialises the {@link LogEventPairValidator} dependency
     *
     * @return The {@link LogEventPairValidator} dependency
     */
    @Bean
    public LogEventPairValidator eventPairValidator() {
        return new LogEventPairValidator();
    }

    /**
     * Initialises the {@link EventGenerator} dependency
     *
     * @param eventDurationCalculator An {@link EventDurationCalculator}
     * @return The {@link EventGenerator} dependency
     */
    @Bean
    public EventGenerator alertGenerator(EventDurationCalculator eventDurationCalculator) {
        return new EventGenerator(eventDurationCalculator);
    }

    /**
     * Initialises the {@link EventDurationCalculator} dependency
     *
     * @return The {@link EventDurationCalculator} dependency
     */
    @Bean
    public EventDurationCalculator eventDurationCalculator() {
        return new EventDurationCalculator();
    }

}
