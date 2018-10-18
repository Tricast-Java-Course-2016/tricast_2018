package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.BetType;

public interface BetTypeManager {

    List<BetType> findAll();

    BetType findById(Long id);

    BetType create(BetType bettype);

    BetType update(BetType bettype);

    void deleteById(Long id);

}
