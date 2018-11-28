package com.tricast.controllers.responses;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionResponse implements Serializable{

	private static final long serialVersionUID = 8033532302109490790L;

	private long transactionId;
	private long betId;
    private String createdDate;
	private String description;
	private BigDecimal amount;
	private long accountId;
    private String type;

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

    public String getCreatedDate() {
		return createdDate;
	}

    public void setCreatedDate(String createdDate) {
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

    public String getType() {
		return type;
	}

    public void setType(String type) {
		this.type = type;
	}

}
