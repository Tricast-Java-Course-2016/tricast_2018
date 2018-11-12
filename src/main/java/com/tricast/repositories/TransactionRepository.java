package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Bet;
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Override
    List<Transaction> findAll();

	Transaction findById(Long id);

	Transaction findByBet(Bet bet);

	Transaction findByType(TransactionTypes transactionType);

    List<Transaction> findByAccount_id(Long id);
}