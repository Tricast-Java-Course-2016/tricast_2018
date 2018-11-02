package com.tricast.controllers;

import java.util.List;

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

@RestController
@RequestMapping(path = "competitors")
public class CompetitorController {

	@Autowired
	private CompetitorManager competitorManager;

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
		return null;
	}

	@PutMapping(path="/{id}")
	public CompetitorResponse update(long id, CompetitorRequest competitorRequest) {
		return null;
	}

	@DeleteMapping(path="/{id}")
	public void delete(long id) {

	}
}
