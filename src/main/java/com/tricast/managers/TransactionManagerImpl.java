package com.tricast.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.repositories.TransactionRepository;

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

}
