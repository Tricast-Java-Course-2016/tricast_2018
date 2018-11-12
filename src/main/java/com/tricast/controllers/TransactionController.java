package com.tricast.controllers;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(path = "/{id}")
    public TransactionResponse findById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping(path = "/filter")
    public List<TransactionResponse> byFilter(
    		@RequestParam(value = "transactionType", required = false) String transactionType,
            @RequestParam(value = "fromDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fromDate,
            @RequestParam(value = "toDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime toDate) {

        // AKOS valahonnét mindenképp kell egy account id is különben minden felahsználó transakcióját visszaadjuk pedig
        // nekünk csak az adott felhasználóé kellenek
        // akár jöhet http headerből is, valahogy így:
        // https://stackoverflow.com/questions/19556039/how-to-get-access-to-http-header-information-in-spring-mvc-rest-controller

        // AKOS nem kell default értek és ez az ellenőrzés felesleges
    	
    	return this.transactionManager.filter(transactionType, fromDate, toDate);
    }

    // Should be merged with the other
    @GetMapping(path = "listByAccountId/{accountid}")
    public List <TransactionResponse> listByAcccountId(@PathVariable("accountid") Long id) {
        return null;
    }

    // Empty URL is fine
    @PostMapping
    public void createTransaction(TransactionRequest request) {
    	transactionManager.create(request);
    }
}
