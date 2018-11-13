package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.MarketManager;
import com.tricast.repositories.entities.Market;

@RestController
@RequestMapping(path = "api/markets")
public class MarketController {

    @Autowired
    private MarketManager marketManager;


    @GetMapping
    public List<Market> findAll() {
        return marketManager.findAll();
    }

    @GetMapping(path = "findById/{id}")
    public Market findById(@PathVariable("id") Long id) {
        return marketManager.findById(id);
    }

}
