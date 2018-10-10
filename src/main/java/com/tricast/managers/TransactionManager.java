package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Transaction;

public interface TransactionManager {

    List<Transaction> findAll();

    Transaction findById(Long id);

    Transaction create(Transaction player);

    Transaction update(Transaction player);

    void deleteById(Long id);

}
