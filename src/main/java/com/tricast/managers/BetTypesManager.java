package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.BetTypes;

public interface BetTypesManager {

    List<BetTypes> findAll();

    BetTypes findById(Long id);

    BetTypes create(BetTypes bettype);

    BetTypes update(BetTypes bettype);

    void deleteById(Long id);

}
