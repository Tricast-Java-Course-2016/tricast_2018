package com.tricast.repositories.customs.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.tricast.repositories.customs.TransactionRepositoryCustom;
import com.tricast.repositories.entities.QTransaction;
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

@Repository
public class TransactionRepositoryCustomImpl extends QueryDslRepositorySupport implements TransactionRepositoryCustom {

    public TransactionRepositoryCustomImpl() {
        super(Transaction.class);
    }

    @Override
    public List<Transaction> filter(String transactionType, OffsetDateTime fromDate, OffsetDateTime toDate) {

        QTransaction transaction = QTransaction.transaction;

        BooleanExpression dateFilter = transaction.createdDate.between(fromDate, toDate);

        JPQLQuery<Transaction> query = from(transaction).where(dateFilter);

        if (StringUtils.hasText(transactionType)) {
            BooleanExpression typeFilter = transaction.type.eq(TransactionTypes.valueOf(transactionType));
            query = query.where(typeFilter);
        }

        return query.fetch();
    }
}
