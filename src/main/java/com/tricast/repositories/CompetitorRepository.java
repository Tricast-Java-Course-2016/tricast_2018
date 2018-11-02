package com.tricast.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tricast.repositories.entities.Competitor;

public interface CompetitorRepository extends CrudRepository<Competitor, Long> {
	@Override
    List<Competitor> findAll();

    Competitor findById(Long id);
    
    @Query(value = "SELECT * FROM tricast.competitors WHERE "
    		+ "id IN ("
    		+ "SELECT eventcompetitormap.competitorid "
    		+ "FROM tricast.eventcompetitormap "
    		+ "WHERE eventcompetitormap.eventid = :eventId)", nativeQuery = true)
    List<Competitor> findByEventId(@Param("eventId") long eventId);
}
