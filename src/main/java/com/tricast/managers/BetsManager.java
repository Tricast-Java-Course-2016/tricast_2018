package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Bets;

public interface BetsManager {

    List<Bets> findAll();

    Bets findById(Long id);

    Bets create(Bets bet);

    Bets update(Bets bet);

    void deleteById(Long id);

}
