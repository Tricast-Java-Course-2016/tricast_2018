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

import com.tricast.managers.EventsManager;
import com.tricast.repositories.entities.Events;

@RestController
@RequestMapping(path = "events")
public class EventsController {

    @Autowired
    private EventsManager eventsManager;

    @Inject
    public EventsController(EventsManager eventsManager) {
        this.eventsManager = eventsManager;
    }

    @GetMapping
    public List<Events> findAll() {
        return eventsManager.findAll();
    }

    @PostMapping
    public Events create(@RequestBody Events event) {
        return eventsManager.create(event);
    }
    

    @GetMapping(path = "findById/{id}")
    public Events findById(@PathVariable("id") Long id) {
        return eventsManager.findById(id);
    }

}
