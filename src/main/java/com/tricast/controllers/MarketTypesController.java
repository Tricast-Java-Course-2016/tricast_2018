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

import com.tricast.managers.MarketTypesManager;
import com.tricast.repositories.entities.MarketTypes;

@RestController
@RequestMapping(path = "markettypes")
public class MarketTypesController {

    @Autowired
    private MarketTypesManager markettypesManager;

    @Inject
    public MarketTypesController(MarketTypesManager markettypesManager) {
        this.markettypesManager = markettypesManager;
    }

    @GetMapping
    public List<MarketTypes> findAll() {
        return markettypesManager.findAll();
    }

    @PostMapping
    public MarketTypes create(@RequestBody MarketTypes markettype) {
        return markettypesManager.create(markettype);
    }
    

    @GetMapping(path = "findById/{id}")
    public MarketTypes findById(@PathVariable("id") Long id) {
        return markettypesManager.findById(id);
    }

}
