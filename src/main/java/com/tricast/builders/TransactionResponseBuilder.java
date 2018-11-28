package com.tricast.builders;

import java.time.format.DateTimeFormatter;

import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.repositories.entities.Transaction;


public class TransactionResponseBuilder {

	public static TransactionResponse build(Transaction transaction) {

		TransactionResponse transactionResponse = new TransactionResponse();

		transactionResponse.setTransactionId(transaction.getId());

		if(transaction.getBet() != null) {
			transactionResponse.setBetId(transaction.getBet().getId());
		}
        transactionResponse
                .setCreatedDate(transaction.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));
		transactionResponse.setDescription(transaction.getDescription());
		transactionResponse.setAmount(transaction.getAmount());
		transactionResponse.setAccountId(transaction.getAccount().getId());
        transactionResponse.setType(transaction.getType().getDescription());

		return transactionResponse;
    }
}
