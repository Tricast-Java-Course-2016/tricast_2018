package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Events;

public interface EventsManager {

    List<Events> findAll();

    Events findById(Long id);

    Events create(Events event);

    Events update(Events event);

    void deleteById(Long id);

}
