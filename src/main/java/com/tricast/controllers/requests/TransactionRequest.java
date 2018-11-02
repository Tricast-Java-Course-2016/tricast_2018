package com.tricast.controllers.requests;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.tricast.repositories.entities.TransactionTypes;

public class TransactionRequest implements Serializable {

	private static final long serialVersionUID = 633645481973175644L;

    // TransactionId?
	private long betId;


    // Use BigDecimal
	private BigDecimal amount;
    // Primitive
	private long accountId;
    // Should be an enum
	private TransactionTypes type;
	
	

	public long getBetId() {
		return betId;
	}
	public void setBetId(long betId) {
		this.betId = betId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public TransactionTypes getType() {
		return type;
	}
	public void setType(TransactionTypes type) {
		this.type = type;
	}

}
