package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.League;

public interface LeagueRepository extends CrudRepository<League, Long> {

	@Override
	List<League> findAll();
}
