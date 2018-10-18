package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.BetTypeManager;
import com.tricast.repositories.entities.BetType;

@RestController
@RequestMapping(path = "bettypes")
public class BetTypeController {

    @Autowired
    private BetTypeManager bettypeManager;


    @GetMapping
    public List<BetType> findAll() {
        return bettypeManager.findAll();
    }


    @GetMapping(path = "findById/{id}")
    public BetType findById(@PathVariable("id") Long id) {
        return bettypeManager.findById(id);
    }

}
