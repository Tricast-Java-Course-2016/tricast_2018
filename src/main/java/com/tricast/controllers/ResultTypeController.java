package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.ResultTypeManager;
import com.tricast.repositories.entities.ResultType;

@RestController
@RequestMapping(path = "resulttypes")
public class ResultTypeController {
	
	@Autowired
	private ResultTypeManager resultTypeManager;
	
	@GetMapping
	public List<ResultType> findAll() {
		return resultTypeManager.findAll();
	}

}
