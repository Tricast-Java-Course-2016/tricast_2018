package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.LeagueRequest;
import com.tricast.controllers.responses.LeagueResponse;
import com.tricast.managers.LeagueManager;

@RestController
@RequestMapping(path = "api/leagues")
public class LeagueController {

	@Autowired
	private LeagueManager leagueManager;

	@GetMapping(path="/{id}")
    public LeagueResponse findById(@PathVariable("id") Long id) {
        return leagueManager.findById(id);
    }

    // Should be query params, both of them.
	@GetMapping(path="/list/{search}")
    public List<LeagueResponse> findAll(String search) {
        return null;
    }

	@GetMapping(path="/list")
    public List<LeagueResponse> findAll() {
		return leagueManager.findAll();
    }

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public LeagueResponse create(@RequestBody LeagueRequest leagueRequest) {
        return leagueManager.create(leagueRequest);
	}

	@PutMapping(path="/{id}")
	public LeagueResponse update(long id, LeagueRequest leagueRequest) {
        return null;
	}

	@DeleteMapping(path="/{id}")
	public void delete(long id) {

	}
}
