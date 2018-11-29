package com.tricast.repositories.entities;

public enum TransactionTypes {
    BET(1, "Bet"), SETTLEMENT(2, "Win"), TRANSFER(3, "Upload"), WITHDRAWAL(4, "Withdraw");

    private final long id;
    private final String description;

    private TransactionTypes(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }
}
