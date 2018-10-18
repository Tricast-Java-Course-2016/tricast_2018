package com.tricast.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.EventManager;
import com.tricast.repositories.entities.Event;

@RestController
@RequestMapping(path = "events")
public class EventController {

    @Autowired
    private EventManager eventManager;

    @GetMapping
    public List<Event> findAll() {
        return eventManager.findAll();
    }

    @GetMapping(path = "findById/{id}")
    public Event findById(@PathVariable("id") Long id) {
        return eventManager.findById(id);
    }

}
