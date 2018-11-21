package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.responses.SportResponse;
import com.tricast.managers.SportManager;
import com.tricast.repositories.entities.Sport;

@RestController
@RequestMapping(path = "api/sports")
public class SportController {

	@Autowired
	private SportManager sportManager;

	@GetMapping
	public List<SportResponse> findAll() {
		return sportManager.findAll();
	}

	@GetMapping(path = "findById/{id}")
	public Sport findById(@PathVariable("id") Long id) {
		return sportManager.findById(id);
	}

}
