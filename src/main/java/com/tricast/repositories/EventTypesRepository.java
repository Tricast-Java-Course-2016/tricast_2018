package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.EventTypes;

public interface EventTypesRepository extends CrudRepository<EventTypes, Long> {

	@Override
    List<EventTypes> findAll();

    EventTypes findById(Long id);

}