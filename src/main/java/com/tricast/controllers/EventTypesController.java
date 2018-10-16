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

import com.tricast.managers.EventTypesManager;
import com.tricast.repositories.entities.EventTypes;

@RestController
@RequestMapping(path = "eventtypes")
public class EventTypesController {

    @Autowired
    private EventTypesManager eventtypesManager;

    @Inject
    public EventTypesController(EventTypesManager eventtypesManager) {
        this.eventtypesManager = eventtypesManager;
    }

    @GetMapping
    public List<EventTypes> findAll() {
        return eventtypesManager.findAll();
    }

    @PostMapping
    public EventTypes create(@RequestBody EventTypes eventtype) {
        return eventtypesManager.create(eventtype);
    }
    

    @GetMapping(path = "findById/{id}")
    public EventTypes findById(@PathVariable("id") Long id) {
        return eventtypesManager.findById(id);
    }

}
