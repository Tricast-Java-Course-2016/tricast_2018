package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.EventCompetitorMap;

public interface EventCompetitorMapRepository extends CrudRepository<EventCompetitorMap, Long> {

	@Override
    List<EventCompetitorMap> findAll();
	
	EventCompetitorMap findById(Long id);
	
	List <EventCompetitorMap> findByCompetitor_Id(Long competitorId);
	
	List <EventCompetitorMap> findByEvent_Id(Long eventId);
}
