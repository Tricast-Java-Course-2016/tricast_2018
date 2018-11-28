package com.tricast.repositories.entities;

public enum TransactionTypes {
    BET("Bet"), SETTLEMENT("Win"), TRANSFER("Upload"), WITHDRAWAL("Withdraw");

    private String description;

    private TransactionTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
