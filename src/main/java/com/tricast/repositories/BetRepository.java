package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Bet;

public interface BetRepository extends CrudRepository<Bet, Long> {

	@Override
    List<Bet> findAll();

    Bet findById(Long id);


}