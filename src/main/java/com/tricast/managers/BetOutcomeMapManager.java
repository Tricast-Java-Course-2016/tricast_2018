package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.BetOutcomeMap;

public interface BetOutcomeMapManager {

    List<BetOutcomeMap> findAll();

    BetOutcomeMap findById(Long id);

    BetOutcomeMap create(BetOutcomeMap betoutcomemap);

    BetOutcomeMap update(BetOutcomeMap betoutcomemap);

    void deleteById(Long id);
}
