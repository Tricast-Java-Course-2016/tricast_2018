package com.tricast.controllers.requests;

import java.io.Serializable;
import java.util.Date;

public class TransactionRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 633645481973175644L;
	
	private Long betId;
	private Date createdDate;
	private String description; 
	private double amount;
	private Long accountId;
	private String type;
	
	public Long getBetId() {
		return betId;
	}
	public void setBetId(Long betId) {
		this.betId = betId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
