package com.tricast.builders;

import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.repositories.entities.Transaction;


public class TransactionResponseBuilder {

	public static TransactionResponse build(Transaction transaction) {

		TransactionResponse transactionResponse = new TransactionResponse();

		transactionResponse.setTransactionId(transaction.getId());

        // AKOS a null pointer alapvetően kerülendő és nem készülünk annak az elkapására
        // (ez igaz minden runtime exceptionre)
        // helyete inkább ellenőrizd null-t ad-e vissza a transaction.getBet() hívás
        // ha nem akkor állísd be az id-t
		
		if(transaction.getBet() != null) {
			transactionResponse.setBetId(transaction.getBet().getId());
		}
		transactionResponse.setCreatedDate(transaction.getCreatedDate());
		transactionResponse.setDescription(transaction.getDescription());
		transactionResponse.setAmount(transaction.getAmount());
		transactionResponse.setAccountId(transaction.getAccount().getId());
		transactionResponse.setType(transaction.getType());

		return transactionResponse;
    }
}
