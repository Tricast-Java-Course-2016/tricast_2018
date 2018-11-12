package com.tricast.managers;

import java.time.OffsetDateTime;
import java.util.List;

import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.managers.exceptions.SportsbookException;


public interface TransactionManager {

    List<TransactionResponse> findAll();

    TransactionResponse findById(Long id);

    TransactionResponse deposit(long accountId, TransactionRequest req) throws SportsbookException;

    TransactionResponse withdraw(long accountId, TransactionRequest req) throws SportsbookException;

    List<TransactionResponse> filter(long accountId, String transactionType, OffsetDateTime fromDate,
            OffsetDateTime toDate);

}
