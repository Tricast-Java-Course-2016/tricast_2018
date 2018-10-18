package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.BetType;

public interface BetTypeRepository extends CrudRepository<BetType, Long> {

	@Override
    List<BetType> findAll();

    BetType findById(Long id);

}