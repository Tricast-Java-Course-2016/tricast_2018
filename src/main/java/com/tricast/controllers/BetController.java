package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.BetManager;
import com.tricast.repositories.entities.Bet;

@RestController
@RequestMapping(path = "bets")
public class BetController {

    @Autowired
    private BetManager betManager;

    @GetMapping
    public List<Bet> findAll() {
        return betManager.findAll();
    }
    
    @GetMapping(path = "findById/{id}")
    public Bet findById(@PathVariable("id") Long id) {
        return betManager.findById(id);
    }

}
