package com.tricast.controllers;

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

    @GetMapping(path = "findById/{id}")
    public TransactionResponse findById(@PathVariable("id") Long id) {
        return null;
    }
    
    @PostMapping
    public TransactionResponse createTransaction(TransactionRequest request) {
    	return null;
    }
    
    @GetMapping(path = "listByBetId/{id}")
    public List <TransactionResponse> listByBetId(@PathVariable("id") Long id) {
        return null;
    }
    
    @GetMapping(path = "listByAccountId/{id}")
    public List <TransactionResponse> listByAcccountId(@PathVariable("id") Long id) {
        return null;
    }
}
