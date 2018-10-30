package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.LeagueRequest;
import com.tricast.controllers.responses.LeagueResponse;
import com.tricast.managers.LeagueManager;

@RestController
@RequestMapping(path = "leagues")
public class LeagueController {

	@Autowired
	private LeagueManager leagueManager;

	@GetMapping(path="/{id}")
    public LeagueResponse findById(@PathVariable("id") Long $id) {
        return null;
    }

    // Should be query params, both of them.
	@GetMapping(path="/list/{search}/{page}")
    public List<LeagueResponse> findAll(String search ,int page) {
        return null;
    }

    // A Response would not hurt
	@PostMapping
	public void create(LeagueRequest leagueRequest) {

	}

    // Response with the new object is mandatory!
	@PutMapping(path="/{id}")
	public void update(LeagueRequest leagueRequest) {

	}

    // Delete would be useful
}
