package com.monitoring.log.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Log event
 */
public class LogEvent {

    @JsonProperty("id")
    private String id;

    @JsonProperty("state")
    private State state;

    @JsonProperty("type")
    private String type;

    @JsonProperty("host")
    private String host;

    @JsonProperty("timestamp")
    private long timestamp;

    public LogEvent() {
    }

    /**
     * Initialises a new {@link LogEvent}
     *
     * @param id        Event identifier
     * @param state     Whether event has started or finished
     * @param type      Type of log
     * @param host      Hostname
     * @param timestamp Timestamp of event in milliseconds
     */
    public LogEvent(String id, State state, String type, String host, long timestamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timestamp = timestamp;
    }

    /**
     * Retrieves the event identifier
     *
     * @return The event identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the state of event
     *
     * @return The state of event
     */
    public State getState() {
        return state;
    }

    /**
     * Retrieves the type of log
     *
     * @return The type of log
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the hostname
     *
     * @return The hostname
     */
    public String getHost() {
        return host;
    }

    /**
     * Retrieves the timestamp of the event in milliseconds
     *
     * @return The timestamp of the event in milliseconds
     */
    public long getTimestamp() {
        return timestamp;
    }

}