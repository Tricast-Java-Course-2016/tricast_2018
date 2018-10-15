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

import com.tricast.managers.ResultTypesManager;
import com.tricast.repositories.entities.ResultTypes;

@RestController
@RequestMapping(path = "resulttypes")
public class ResultTypesController {
	
	@Autowired
	private ResultTypesManager resultTypesManager;
	
	@Inject
	public ResultTypesController(ResultTypesManager resultTypesManager) {
		this.resultTypesManager=resultTypesManager;
}
	@GetMapping
	public List<ResultTypes> findAll() {
		return resultTypesManager.findAll();
	}
	@GetMapping(path = "findById/{id}")
	public ResultTypes findById(@PathVariable("id") Long id) {
		return resultTypesManager.findById(id);
	}
	@PostMapping
	public ResultTypes create(@RequestBody ResultTypes resulttype) {
		return resultTypesManager.create(resulttype);
	}
	
}
