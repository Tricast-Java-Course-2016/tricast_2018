package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.BetOutcomeMap;

public interface BetOutcomeMapRepository extends CrudRepository<BetOutcomeMap, Long> {

	@Override
    List<BetOutcomeMap> findAll();

	BetOutcomeMap findById(Long id);
	
	List <BetOutcomeMap> findByBet_Id(Long betId);
	
}