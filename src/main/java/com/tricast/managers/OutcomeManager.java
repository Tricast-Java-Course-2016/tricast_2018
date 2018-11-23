package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Outcome;

public interface OutcomeManager {

    List<Outcome> findAll();

    Outcome findById(Long id);

    Outcome create(Outcome outcome);

    Outcome update(Outcome outcome);

    void deleteById(Long id);
    
    List<Outcome> findByEventId(long eventId);
}
