package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.CompetitorManager;
import com.tricast.repositories.entities.Competitor;

@RestController
@RequestMapping(path = "competitors")
public class CompetitorController {

	@Autowired
	private CompetitorManager competitorManager;
	
	@GetMapping(path="/{id}")
    public Competitor findById(@PathVariable("id") Long $id) {
        return competitorManager.findById($id);
    }
	
	@GetMapping
    public List<Competitor> findAll() {
        return competitorManager.findAll();
    }
}
