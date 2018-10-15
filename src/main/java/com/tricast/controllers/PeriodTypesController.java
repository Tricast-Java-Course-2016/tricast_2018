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

import com.tricast.managers.PeriodTypesManager;
import com.tricast.repositories.entities.PeriodTypes;

@RestController
@RequestMapping(path = "periodtypes")
public class PeriodTypesController {

    @Autowired
    private PeriodTypesManager periodtypesManager;

    @Inject
    public PeriodTypesController(PeriodTypesManager periodtypesManager) {
        this.periodtypesManager = periodtypesManager;
    }

    @GetMapping
    public List<PeriodTypes> findAll() {
        return periodtypesManager.findAll();
    }

    @PostMapping
    public PeriodTypes create(@RequestBody PeriodTypes periodtype) {
        return periodtypesManager.create(periodtype);
    }
    

    @GetMapping(path = "findById/{id}")
    public PeriodTypes findById(@PathVariable("id") Long id) {
        return periodtypesManager.findById(id);
    }

}
