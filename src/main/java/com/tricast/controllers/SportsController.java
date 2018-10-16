package com.tricast.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.SportsManager;
import com.tricast.repositories.entities.Sports;

@RestController
@RequestMapping(path = "sports")
public class SportsController {
	
	@Autowired
	private SportsManager sportsManager;
	
	@Inject
	public SportsController(SportsManager sportsManager) {
		this.sportsManager=sportsManager;
}
	@GetMapping
	public List<Sports> findAll() {
		return sportsManager.findAll();
	}
	@GetMapping(path = "findById/{id}")
	public Sports findById(@PathVariable("id") Long id) {
		return sportsManager.findById(id);
	}
	@PostMapping
	public Sports create(@RequestBody Sports sport) {
		return sportsManager.create(sport);
	}
	
}
