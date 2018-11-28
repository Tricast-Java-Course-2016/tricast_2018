package com.tricast.managers;

import java.util.List;

import com.tricast.controllers.requests.LeagueRequest;
import com.tricast.controllers.responses.LeagueResponse;
import com.tricast.repositories.entities.League;;

public interface LeagueManager {

	List<LeagueResponse> findAll();

	LeagueResponse findById(Long id);

	League update(League league);

    void deleteById(Long id);

	LeagueResponse create(LeagueRequest leagueRequest);
}
