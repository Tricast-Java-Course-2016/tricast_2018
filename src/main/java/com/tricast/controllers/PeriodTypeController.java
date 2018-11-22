package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.PeriodTypeManager;
import com.tricast.repositories.entities.PeriodType;

@RestController
@RequestMapping(path = "api/periodtypes")
public class PeriodTypeController {

    @Autowired
    private PeriodTypeManager periodtypeManager;

    @GetMapping
    public List<PeriodType> findAll() {
        return periodtypeManager.findAll();
    }

    @GetMapping(path = "findById/{id}")
    public PeriodType findById(@PathVariable("id") int id) {
        return periodtypeManager.findById(id);
    }
    
    @GetMapping(path = "findByEventId/{id}")
    public List<PeriodType> findByEventId(@PathVariable("id") long eventId){
    	return periodtypeManager.findByEventId(eventId);
    }

}
