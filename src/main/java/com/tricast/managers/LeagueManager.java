package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.League;;

public interface LeagueManager {

	List<League> findAll();

	League findById(Long id);

	League create(League league);

	League update(League league);

    void deleteById(Long id);
}
