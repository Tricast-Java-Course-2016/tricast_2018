package com.tricast.controllers.responses;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountBalanceResponse implements Serializable {

    private static final long serialVersionUID = 1047452895564266279L;

    private BigDecimal balance;

    public AccountBalanceResponse() {
    }

    public AccountBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccountBalanceResponse other = (AccountBalanceResponse) obj;
        if (balance == null) {
            if (other.balance != null) {
                return false;
            }
        } else if (!balance.equals(other.balance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AccountBalanceResponse [balance=" + balance + "]";
    }
}
