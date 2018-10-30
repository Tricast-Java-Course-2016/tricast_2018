package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.BetOutcomeMapRequest;
import com.tricast.controllers.responses.BetOutcomeMapResponse;
import com.tricast.managers.BetOutcomeMapManager;

@RestController
@RequestMapping(path = "betoutcomemap")
public class BetOutcomeMapController {

    // Remove completely

    @Autowired
    private BetOutcomeMapManager betoutcomemapManager;

    @GetMapping
    public List <BetOutcomeMapResponse> findAll() {
        return null;
    }

    @GetMapping(path = "listByBetId/{id}")
    public List <BetOutcomeMapResponse> listById(@PathVariable("betid") Long id) {
        return null;
    }

    @PostMapping(path="???")
    public BetOutcomeMapResponse addOutcomeToBet(BetOutcomeMapRequest request) {
    	return null;
    }

    @PostMapping(path="???")
    public BetOutcomeMapResponse removeOutcomeFromBet(BetOutcomeMapRequest request) {
    	return null;
    }
}
