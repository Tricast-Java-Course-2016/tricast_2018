package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.EventCompetitorMap;
import com.tricast.repositories.entities.LeagueCompetitorMap;

public interface LeagueCompetitorMapRepository extends CrudRepository<LeagueCompetitorMap, Long> {
	
	@Override
    List<LeagueCompetitorMap> findAll();
	
	LeagueCompetitorMap findById(Long id);
	
	List <LeagueCompetitorMap> findByCompetitor_Id(Long competitorId);
	
	List <LeagueCompetitorMap> findByLeague_Id(Long leagueId);
}
