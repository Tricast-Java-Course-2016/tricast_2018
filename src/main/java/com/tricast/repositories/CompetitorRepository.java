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

    // AKOS Ha már SQL akkor JOIN-olni kéne itt
    // de nem is kell ez ide mivel rá lehet tenni a Competitorokat az Event-re és akkor csak egy event.getCompetitors
    // hívás
    @Query(value = "SELECT * FROM tricast.competitors WHERE "
    		+ "id IN ("
    		+ "SELECT eventcompetitormap.competitorid "
    		+ "FROM tricast.eventcompetitormap "
    		+ "WHERE eventcompetitormap.eventid = :eventId)", nativeQuery = true)
    List<Competitor> findByEventId(@Param("eventId") long eventId);
}
