package com.tricast.managers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.builders.EventResponseBuilder;
import com.tricast.builders.TransactionResponseBuilder;
import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.EventResponse;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.repositories.TransactionRepository;
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

@Service
public class TransactionManagerImpl implements TransactionManager {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponse> findAll() {
        return null;
    }

    @Override
    public TransactionResponse findById(Long id) {
        return null;
    }

    @Override
    public TransactionResponse create(TransactionRequest player) {
        return null;
    }

    @Override
    public TransactionResponse update(TransactionRequest player) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
    }
    
    @Override
	public List<TransactionResponse> filter(Calendar fromDate, Calendar toDate) {
    	
    	List<Transaction> transactions = transactionRepository.filter(fromDate, toDate);
    	
    	
    	/*
		List<Transaction> transactions = TransactionRepository.filter(
				transactionType == null ? "" : transactionType, 
				fromDate,
				toDate
			);
			
		*/
		List<TransactionResponse> transactionResponses = new ArrayList<TransactionResponse>();
		for (Transaction transaction : transactions) {
			transactionResponses.add(TransactionResponseBuilder.build(transaction));
		}
		return transactionResponses;
	}

}
