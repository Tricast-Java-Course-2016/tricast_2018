package com.tricast.repositories.customs;

import java.time.OffsetDateTime;
import java.util.List;

import com.tricast.repositories.entities.Transaction;

public interface TransactionRepositoryCustom {

    List<Transaction> filter(String transactionType, OffsetDateTime fromDate, OffsetDateTime toDate);
}
