package com.tricast.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.managers.TransactionManager;
import com.tricast.repositories.entities.Transaction;

@RestController
@RequestMapping(path = "transactions")
public class TransactionController {

    @Autowired
    private TransactionManager transactionManager;

    @GetMapping(path = "findById/{id}")
    public Transaction findById(@PathVariable("id") Long id) {
        return transactionManager.findById(id);
    }
}
