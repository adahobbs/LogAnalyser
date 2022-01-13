package com.monitoring.log.event;

import com.monitoring.log.event.model.LogEvent;

/**
 * Calculates the duration of an {@link com.monitoring.log.database.Event}
 */
public class EventDurationCalculator {

    /**
     * Calculates the duration of an {@link com.monitoring.log.database.Event} by taking the time difference between
     * the provided log events
     *
     * @param logEvent1 First {@link LogEvent}
     * @param logEvent2 Second {@link LogEvent}
     * @return Duration of the {@link com.monitoring.log.database.Event}
     */
    public long calculate(LogEvent logEvent1, LogEvent logEvent2) {
        return Math.abs(logEvent1.getTimestamp() - logEvent2.getTimestamp());
    }

}