package com.monitoring.log.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Event Entity
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "duration")
    private long duration;

    @Column(name = "type")
    private String type;

    @Column(name = "host")
    private String host;

    @Column(name = "alert")
    private Boolean alert;

    public Event() {
    }

    /**
     * Initialises a new {@link Event}
     *
     * @param id       Event ID
     * @param duration Event Duration
     * @param type     Event Type
     * @param host     Event Host
     * @param alert    True if event should be flagged, false otherwise
     */
    public Event(String id, long duration, String type, String host, boolean alert) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.host = host;
        this.alert = alert;
    }

}
