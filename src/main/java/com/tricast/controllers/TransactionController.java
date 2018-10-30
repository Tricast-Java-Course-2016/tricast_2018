package com.tricast.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.managers.TransactionManager;

@RestController
@RequestMapping(path = "transactions")
public class TransactionController {

    @Autowired
    private TransactionManager transactionManager;

    @GetMapping
    public List<TransactionResponse> findAll() {
        return null;
    }

    // ID URL enough
    @GetMapping(path = "findById/{id}")
    public TransactionResponse findById(@PathVariable("id") Long id) {
        return null;
    }

    // RequestParam
    @GetMapping(path = "{date}/{name}")
    public List <TransactionResponse> findByDateAndName(@PathVariable("date") Calendar date, @PathVariable("name") String name) {
    	return null;
    }

    // Empty URL is fine
    @PostMapping(path="???")
    public TransactionResponse createTransaction(TransactionRequest request) {
    	return null;
    }

    // Can be removed
    @GetMapping(path = "listByBetId/{betid}")
    public List <TransactionResponse> listByBetId(@PathVariable("betid") Long id) {
        return null;
    }

    // Should be merged with the other
    @GetMapping(path = "listByAccountId/{accountid}")
    public List <TransactionResponse> listByAcccountId(@PathVariable("accountid") Long id) {
        return null;
    }
}
