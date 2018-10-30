package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.CompetitorRequest;
import com.tricast.controllers.responses.CompetitorResponse;
import com.tricast.managers.CompetitorManager;

@RestController
@RequestMapping(path = "competitors")
public class CompetitorController {

	@Autowired
	private CompetitorManager competitorManager;

	@GetMapping(path="/{id}")
    public CompetitorResponse findById(@PathVariable("id") Long $id) {
        return null;
    }

    // Should be query params, both of them.
	@GetMapping(path="/list/{search}/{page}")
    public List<CompetitorResponse> findAll(String search ,int page) {
        return null;
    }

    // A Response would not hurt
	@PostMapping
	public void create(CompetitorRequest competitorRequest) {

	}

    // Response with the new object is mandatory!
	@PutMapping(path="/{id}")
	public void update(CompetitorRequest competitorRequest) {

	}

    // Delete would be useful
}
