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

import com.tricast.managers.BetsManager;
import com.tricast.repositories.entities.Bets;

@RestController
@RequestMapping(path = "bets")
public class BetsController {

    @Autowired
    private BetsManager betsManager;

    @Inject
    public BetsController(BetsManager betsManager) {
        this.betsManager = betsManager;
    }

    @GetMapping
    public List<Bets> findAll() {
        return betsManager.findAll();
    }

    @PostMapping
    public Bets create(@RequestBody Bets bet) {
        return betsManager.create(bet);
    }
    

    @GetMapping(path = "findById/{id}")
    public Bets findById(@PathVariable("id") Long id) {
        return betsManager.findById(id);
    }

}
