package com.tricast.managers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.repositories.TransactionRepository;
import com.tricast.repositories.entities.Transaction;

@Service
public class TransactionManagerImpl implements TransactionManager {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findOne(id);
    }

    @Override
    public Transaction create(Transaction player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Transaction update(Transaction player) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

}
