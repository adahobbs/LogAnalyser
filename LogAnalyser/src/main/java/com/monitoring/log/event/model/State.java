package com.monitoring.log.event.model;

/**
 * State of an {@link LogEvent}
 */
public enum State {

    STARTED("STARTED"), FINISHED("FINISHED");

    private final String value;

    /**
     * Initialises a new {@link State}
     *
     * @param value State value
     */
    State(String value) {
        this.value = value;
    }

    /**
     * Retrieves the state value
     *
     * @return The state value
     */
    public String getValue() {
        return value;
    }

}