package com.tricast.repositories;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tricast.repositories.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

	@Override
    List<Event> findAll();

    Event findById(Long id);
    
    @Query(value = "SELECT * FROM tricast.events WHERE "
    		+ "starttime BETWEEN :fromDate AND :toDate AND"
    		+ "(description ILIKE :search) AND"
    		+ "(:sportId = -1 OR (SELECT leagues.sportid FROM tricast.leagues WHERE leagues.id = events.leagueid) = :sportId) AND"
    		+ "(:leagueId = -1 OR leagueid = :leagueId)", nativeQuery = true)
    List<Event> filter(
    		@Param("search") String search, 
    		@Param("sportId") Long sportId, 
    		@Param("leagueId") Long leagueId,
    		@Param("fromDate") Calendar fromDate,
    		@Param("toDate") Calendar toDate);
}