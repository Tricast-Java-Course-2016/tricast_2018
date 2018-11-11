package com.tricast.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.managers.CompetitorManager;
import com.tricast.managers.LeagueManager;
import com.tricast.repositories.entities.Competitor;
import com.tricast.repositories.entities.League;

@RestController
@RequestMapping(path = "competitors")
public class CompetitorController {

	@Autowired
	private CompetitorManager competitorManager;
	@Autowired
	private LeagueManager leagueManager;

	@GetMapping(path="/{id}")
    public CompetitorResponse findById(@PathVariable("id") long $id) {
        return null;
    }

    // Should be query params, both of them.
	@GetMapping(path="/list/{search}")
    public List<CompetitorResponse> findAll(String search) {
        return null;
    }
	
	@GetMapping(path="/list}")
    public List<CompetitorResponse> findAll() {
        return null;
    }

	@PostMapping
	public CompetitorResponse create(CompetitorRequest competitorRequest) {
		Competitor competitor = new Competitor();
		competitor.setDescription(competitorRequest.getDescription());
		
		
		List<Long> leagueIds = competitorRequest.getLeagueIds();
		Set<League> leagues = new HashSet<League>();
		
		for (Long id : leagueIds) {
			leagues.add(leagueManager.findById(id));
		}
		
		competitor.setLeagues(leagues);
		competitor = competitorManager.create(competitor);
		
		CompetitorResponse response = new CompetitorResponse();
		response.setId(competitor.getId());
		response.setDescription(competitor.getDescription());
		return response;
	}

	@PutMapping(path="/{id}")
	public CompetitorResponse update(long id, CompetitorRequest competitorRequest) {
		return null;
	}

	@DeleteMapping(path="/{id}")
	public void delete(long id) {

	}
}
