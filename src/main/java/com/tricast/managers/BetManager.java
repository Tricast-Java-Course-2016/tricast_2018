package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Bet;

public interface BetManager {

    List<Bet> findAll();

    Bet findById(Long id);

    Bet create(Bet bet);

    Bet update(Bet bet);

    void deleteById(Long id);

}
