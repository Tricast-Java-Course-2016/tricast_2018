package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Bets;

public interface BetsRepository extends CrudRepository<Bets, Long> {

	@Override
    List<Bets> findAll();

    Bets findById(Long id);

}