package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.LeagueCompetitorMap;

public interface LeagueCompetitorMapRepository extends CrudRepository<LeagueCompetitorMap, Long> {
	
	@Override
    List<LeagueCompetitorMap> findAll();
	
	LeagueCompetitorMap findById(Long id);
	
	List <LeagueCompetitorMap> findByCompetitorId(Long competitorId);
	
	List <LeagueCompetitorMap> findByLeagueId(Long leagueId);
}
