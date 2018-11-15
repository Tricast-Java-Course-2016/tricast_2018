package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.League;

public interface LeagueRepository extends CrudRepository<League, Long> {

	League findById(Long id);
	
	@Override
	List<League> findAll();
	
	League findByDescriptionLike(String description);
}
