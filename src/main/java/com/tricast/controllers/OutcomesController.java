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

import com.tricast.managers.OutcomesManager;
import com.tricast.repositories.entities.Outcomes;

@RestController
@RequestMapping(path = "outcomes")
public class OutcomesController {

    @Autowired
    private OutcomesManager outcomesManager;

    @Inject
    public OutcomesController(OutcomesManager outcomesManager) {
        this.outcomesManager = outcomesManager;
    }

    @GetMapping
    public List<Outcomes> findAll() {
        return outcomesManager.findAll();
    }

    @PostMapping
    public Outcomes create(@RequestBody Outcomes outcome) {
        return outcomesManager.create(outcome);
    }
    

    @GetMapping(path = "findById/{id}")
    public Outcomes findById(@PathVariable("id") Long id) {
        return outcomesManager.findById(id);
    }

}
