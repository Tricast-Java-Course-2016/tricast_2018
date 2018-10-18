package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.EventTypeManager;
import com.tricast.repositories.entities.EventType;

@RestController
@RequestMapping(path = "eventtypes")
public class EventTypeController {

    @Autowired
    private EventTypeManager eventtypeManager;


    @GetMapping
    public List<EventType> findAll() {
        return eventtypeManager.findAll();
    }

    @GetMapping(path = "findById/{id}")
    public EventType findById(@PathVariable("id") Long id) {
        return eventtypeManager.findById(id);
    }

}
