package com.monitoring.log.event;

import com.monitoring.log.event.model.LogEvent;

import java.util.Objects;

/**
 * Validates that provided {@link LogEvent}s are a pair
 */
public class LogEventPairValidator {

    /**
     * Return whether {@code logEvent1} and {@code logEvent2} are a pair.
     * <p>
     * Checks whether one log event has state {@link com.monitoring.log.event.model.State.STARTED} and the other has
     * state {@link com.monitoring.log.event.model.State.FINISHED}
     *
     * @param logEvent1 First {@link LogEvent}
     * @param logEvent2 Second {@link LogEvent}
     * @return True if the log events are a pair, false otherwise
     */
    public boolean arePair(LogEvent logEvent1, LogEvent logEvent2) {
        return Objects.nonNull(logEvent1) && Objects.nonNull(logEvent2)
                && Objects.nonNull(logEvent1.getState()) && Objects.nonNull(logEvent2.getState())
                && logEvent1.getState() != logEvent2.getState();
    }

}