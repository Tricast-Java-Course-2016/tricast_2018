package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.MarketType;

public interface MarketTypeRepository extends CrudRepository<MarketType, Long> {

	@Override
    List<MarketType> findAll();

    MarketType findById(Long id);

}