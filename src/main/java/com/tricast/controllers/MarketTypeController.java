package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.MarketTypeManager;
import com.tricast.repositories.entities.MarketType;

@RestController
@RequestMapping(path = "api/markettypes")
public class MarketTypeController {

    @Autowired
    private MarketTypeManager markettypeManager;

    @GetMapping
    public List<MarketType> findAll() {
        return markettypeManager.findAll();
    }

    @GetMapping(path = "findById/{id}")
    public MarketType findById(@PathVariable("id") Long id) {
        return markettypeManager.findById(id);
    }

}
