package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.BetOutcomeMapRequest;
import com.tricast.controllers.responses.BetOutcomeMapResponse;
import com.tricast.managers.BetOutcomeMapManager;
import com.tricast.repositories.entities.BetOutcomeMap;

@RestController
@RequestMapping(path = "betoutcomemap")
public class BetOutcomeMapController {

    @Autowired
    private BetOutcomeMapManager betoutcomemapManager;

    @GetMapping
    public List<BetOutcomeMapResponse> findAll() {
        return null;
    }

    @GetMapping(path = "findById/{id}")
    public BetOutcomeMapResponse findById(@PathVariable("id") Long id) {
        return null;
    }

}
