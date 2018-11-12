package com.tricast.controllers.requests;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionRequest implements Serializable {

	private static final long serialVersionUID = 633645481973175644L;

	private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
