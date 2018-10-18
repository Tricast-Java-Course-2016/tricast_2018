package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	@Override
    List<Event> findAll();

    Event findById(Long id);

}