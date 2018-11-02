package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.responses.EventDetailResponse;
import com.tricast.repositories.entities.Event;

public interface EventManager {

    List<Event> findAll();

    Event findById(Long id);

    Event create(Event event);

    Event update(Event event);

    void deleteById(Long id);
    
    EventDetailResponse detail(Long id);

}
