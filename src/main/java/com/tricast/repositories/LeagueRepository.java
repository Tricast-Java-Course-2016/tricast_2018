package com.tricast.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.League;

public interface LeagueRepository extends CrudRepository<League, Long> {

}
