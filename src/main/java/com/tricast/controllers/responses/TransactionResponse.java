package com.tricast.controllers.responses;

import java.io.Serializable;
import java.util.Date;

public class TransactionResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 8033532302109490790L;

    // TransactionId?
	private Long betId;
    // OffsetDateTime
	private Date createdDate;
	private String description;
    // BigDecimal
	private double amount;
    // Primitive
	private Long accountId;
    // Enum
	private String type;

	public Long getBetId() {
		return betId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public String getDescription() {
		return description;
	}
	public double getAmount() {
		return amount;
	}
	public Long getAccountId() {
		return accountId;
	}
	public String getType() {
		return type;
	}

}
