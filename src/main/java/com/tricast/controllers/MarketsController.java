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

import com.tricast.managers.MarketsManager;
import com.tricast.repositories.entities.Markets;

@RestController
@RequestMapping(path = "markets")
public class MarketsController {

    @Autowired
    private MarketsManager marketsManager;

    @Inject
    public MarketsController(MarketsManager marketsManager) {
        this.marketsManager = marketsManager;
    }

    @GetMapping
    public List<Markets> findAll() {
        return marketsManager.findAll();
    }

    @PostMapping
    public Markets create(@RequestBody Markets market) {
        return marketsManager.create(market);
    }
    

    @GetMapping(path = "findById/{id}")
    public Markets findById(@PathVariable("id") Long id) {
        return marketsManager.findById(id);
    }

}
