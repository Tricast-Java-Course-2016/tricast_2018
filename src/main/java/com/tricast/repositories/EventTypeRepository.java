package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.EventType;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {

	@Override
    List<EventType> findAll();

    EventType findById(Long id);

}