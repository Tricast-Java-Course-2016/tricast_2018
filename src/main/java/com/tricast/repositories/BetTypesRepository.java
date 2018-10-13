package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.BetTypes;

public interface BetTypesRepository extends CrudRepository<BetTypes, Long> {

	@Override
    List<BetTypes> findAll();

    BetTypes findById(Long id);

}