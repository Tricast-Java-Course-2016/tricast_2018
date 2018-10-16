package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.MarketTypes;

public interface MarketTypesRepository extends CrudRepository<MarketTypes, Long> {

	@Override
    List<MarketTypes> findAll();

    MarketTypes findById(Long id);

}