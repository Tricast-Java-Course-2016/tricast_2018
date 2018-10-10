package com.tricast.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}