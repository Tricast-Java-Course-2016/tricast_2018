package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.responses.EventTypeResponse;
import com.tricast.managers.EventTypeManager;

@RestController
@RequestMapping(path = "api/eventtypes")
public class EventTypeController {

    @Autowired
    private EventTypeManager eventtypeManager;


    @GetMapping
    public List<EventTypeResponse> findAll() {
        return null;
    }

    @GetMapping(path = "findById/{id}")
    public EventTypeResponse findById(@PathVariable("id") Long id) {
        return null;
    }

}
