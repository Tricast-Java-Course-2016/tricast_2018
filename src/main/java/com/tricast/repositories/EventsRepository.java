package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Events;

public interface EventsRepository extends CrudRepository<Events, Long> {

	@Override
    List<Events> findAll();

    Events findById(Long id);

}