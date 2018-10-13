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

import com.tricast.managers.BetTypesManager;
import com.tricast.repositories.entities.BetTypes;

@RestController
@RequestMapping(path = "bettypes")
public class BetTypesController {

    @Autowired
    private BetTypesManager bettypesManager;

    @Inject
    public BetTypesController(BetTypesManager bettypesManager) {
        this.bettypesManager = bettypesManager;
    }

    @GetMapping
    public List<BetTypes> findAll() {
        return bettypesManager.findAll();
    }

    @PostMapping
    public BetTypes create(@RequestBody BetTypes bettype) {
        return bettypesManager.create(bettype);
    }
    

    @GetMapping(path = "findById/{id}")
    public BetTypes findById(@PathVariable("id") Long id) {
        return bettypesManager.findById(id);
    }

}
