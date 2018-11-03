package com.tricast.repositories;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tricast.repositories.entities.Bet;
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	@Override
    List<Transaction> findAll();
	
	Transaction findById(Long id);
	Transaction findByBet(Bet bet);
	
	Transaction findByTransactionType(TransactionTypes transactionType);
	
    @Query(value = "SELECT * FROM tricast.transactions WHERE "
    		+ "createdDate BETWEEN :fromDate AND :toDate AND"
    		+ "(type = :transactionType)", nativeQuery = true)
    
	static List<Transaction> filter(
    		@Param("transactionType") TransactionTypes transactionType, 
    		@Param("fromDate") Calendar fromDate,
    		@Param("toDate") Calendar toDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
}