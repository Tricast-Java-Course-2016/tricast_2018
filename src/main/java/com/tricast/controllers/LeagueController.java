package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.LeagueManager;
import com.tricast.repositories.entities.League;

@RestController
@RequestMapping(path = "leagues")
public class LeagueController {

	@Autowired
	private LeagueManager leagueManager;
	
	@GetMapping(path="/{id}")
    public League findById(@PathVariable("id") Long $id) {
        return leagueManager.findById($id);
    }
	
	@GetMapping
    public List<League> findAll() {
        return leagueManager.findAll();
    }
}
