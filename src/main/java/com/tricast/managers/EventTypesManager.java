package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.EventTypes;

public interface EventTypesManager {

    List<EventTypes> findAll();

    EventTypes findById(Long id);

    EventTypes create(EventTypes eventtype);

    EventTypes update(EventTypes eventtype);

    void deleteById(Long id);

}
