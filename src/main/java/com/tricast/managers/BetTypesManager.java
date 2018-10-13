package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.BetTypes;

public interface BetTypesManager {

    List<BetTypes> findAll();

    BetTypes findById(Long id);

    BetTypes create(BetTypes player);

    BetTypes update(BetTypes player);

    void deleteById(Long id);

}
