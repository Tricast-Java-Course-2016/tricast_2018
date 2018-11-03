package com.tricast.managers;

import java.util.Calendar;
import java.util.List;

import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.repositories.entities.TransactionTypes;


public interface TransactionManager {

    List<TransactionResponse> findAll();

    TransactionResponse findById(Long id);

    TransactionResponse create(TransactionRequest player);

    TransactionResponse update(TransactionRequest player);

    void deleteById(Long id);
    
    List<TransactionResponse> filter(String transactionType, Calendar fromDate, Calendar toDate);

}
