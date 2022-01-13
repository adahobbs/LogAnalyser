package com.monitoring.log.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for holding the {@link Event} collection
 */
@Repository
public interface EventRepository extends CrudRepository<Event, String> {
}