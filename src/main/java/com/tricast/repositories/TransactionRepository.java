package com.tricast.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tricast.repositories.entities.Bet;
import com.tricast.repositories.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	@Override
    List<Transaction> findAll();
	
	Transaction findById(Long id);
	Transaction findByBet(Bet bet);
	
}