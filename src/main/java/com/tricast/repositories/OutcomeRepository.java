package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Outcome;

public interface OutcomeRepository extends CrudRepository<Outcome, Long> {

	@Override
    List<Outcome> findAll();

    Outcome findById(Long id);
    
    List <Outcome> findByMarket_Id(long marketId);
    
    List<Outcome> findByMarket_Event_Id(long eventId);
}