package com.tricast.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.OutcomeManager;
import com.tricast.repositories.entities.Outcome;
import com.tricast.repositories.entities.Result;

@RestController
@RequestMapping(path = "api/outcomes")
public class OutcomeController {

    @Autowired
    private OutcomeManager outcomeManager;

    @GetMapping
    public List<Outcome> findAll() {
        return outcomeManager.findAll();
    }

    @GetMapping(path = "findById/{id}")
    public Outcome findById(@PathVariable("id") Long id) {
        return outcomeManager.findById(id);
    }
    
    @GetMapping(path = "/test/{id}")
    public Set<Result> findByEventId(@PathVariable("id") long id){
    	return outcomeManager.findByEventId(id);
    }
}
