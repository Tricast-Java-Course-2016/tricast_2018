package com.tricast.builders;

import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.repositories.entities.Transaction;


public class TransactionResponseBuilder {
	
	public static TransactionResponse build(Transaction transaction) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		
		transactionResponse.setTransactionId(transaction.getId());
		try {
			transactionResponse.setBetId(transaction.getBet().getId());
		}catch (NullPointerException npe) {
			transactionResponse.setBetId(0);
		}
		transactionResponse.setCreatedDate(transaction.getCreatedDate());
		transactionResponse.setDescription(transaction.getDescription());
		transactionResponse.setAmount(transaction.getAmount());
		transactionResponse.setAccountId(transaction.getAccount().getId());
		transactionResponse.setType(transaction.getType());
        
		return transactionResponse;
    }
}
