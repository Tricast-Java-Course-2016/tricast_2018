package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Market;
import com.tricast.repositories.entities.Outcome;

public interface OutcomeRepository extends CrudRepository<Outcome, Long> {

	@Override
    List<Outcome> findAll();

    Outcome findById(Long id);
    
    List <Outcome> findByMarketId(Market Market);

}