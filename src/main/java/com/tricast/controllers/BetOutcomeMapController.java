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

import com.tricast.managers.BetOutcomeMapManager;
import com.tricast.repositories.entities.BetOutcomeMap;

@RestController
@RequestMapping(path = "betoutcomemap")
public class BetOutcomeMapController {

    @Autowired
    private BetOutcomeMapManager betoutcomemapManager;

    @Inject
    public BetOutcomeMapController(BetOutcomeMapManager boMapManager) {
        this.betoutcomemapManager = boMapManager;
    }

    @GetMapping
    public List<BetOutcomeMap> findAll() {
        return betoutcomemapManager.findAll();
    }

    @PostMapping
    public BetOutcomeMap create(@RequestBody BetOutcomeMap betoutcomemap) {
        return betoutcomemapManager.create(betoutcomemap);
    }    

    @GetMapping(path = "findById/{id}")
    public BetOutcomeMap findById(@PathVariable("id") Long id) {
        return betoutcomemapManager.findById(id);
    }

}
