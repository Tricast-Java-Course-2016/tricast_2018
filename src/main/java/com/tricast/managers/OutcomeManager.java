package com.tricast.managers;

import java.util.List;
import java.util.Set;

import com.tricast.repositories.entities.Bet;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.Result;

public interface OutcomeManager {

    List<Outcome> findAll();

    Outcome findById(Long id);

    Outcome create(Outcome outcome);

    Outcome update(Outcome outcome);

    void deleteById(Long id);
    
    Set<Result> findByEventId(long eventId);
}
