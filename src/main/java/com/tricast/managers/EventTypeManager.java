package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.EventType;

public interface EventTypeManager {

    List<EventType> findAll();

    EventType findById(Long id);

    EventType create(EventType eventtype);

    EventType update(EventType eventtype);

    void deleteById(Long id);

}
