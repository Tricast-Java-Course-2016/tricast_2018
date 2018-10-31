package com.tricast.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.responses.BetResponse;
import com.tricast.managers.BetManager;


@RestController
@RequestMapping(path = "bets")
public class BetController {

    @Autowired
    private BetManager betManager;

    @GetMapping
    public List<BetResponse> findAll() {
        return null;
    }

    // URL not needed only PathVariable
    @GetMapping(path = "findById/{id}")
    public BetResponse findById(@PathVariable("id") Long id) {
        return null;
    }

    // URL empty, use BetRequest, put betTypeId and accountId in the Request.
    @PostMapping(path="createBet/{bettypeid}/{accountid} ???")
    public BetResponse createBet(@RequestBody BetResponse newBet, @PathVariable ("bettypeid") Long bettypeid, @PathVariable("accountid") Long accountid) {
    	return null;
    }

}
