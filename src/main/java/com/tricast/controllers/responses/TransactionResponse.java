package com.tricast.controllers.responses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

import com.tricast.repositories.entities.TransactionTypes;

public class TransactionResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8033532302109490790L;

    // TransactionId?
	private long transactionId;
	
	private long betId;
    // OffsetDateTime
	private OffsetDateTime createdDate;
	
	private String description;
    // BigDecimal
	private BigDecimal amount;
    // Primitive
	private long accountId;
    // Enum
	private TransactionTypes type;
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getBetId() {
		return betId;
	}
	public void setBetId(long betId) {
		this.betId = betId;
	}
	public OffsetDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(OffsetDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
