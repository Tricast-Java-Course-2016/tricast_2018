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
    public List<Transaction> filter(long accountId, String transactionType, OffsetDateTime fromDate,
            OffsetDateTime toDate) {

        QTransaction transaction = QTransaction.transaction;

        JPQLQuery<Transaction> query = from(transaction).where(transaction.account().id.eq(accountId));

        if (fromDate != null) {
            BooleanExpression fromDateFilter = transaction.createdDate.after(fromDate);
            query = query.where(fromDateFilter);
        }

        if (toDate != null) {
            BooleanExpression toDateFilter = transaction.createdDate.before(toDate);
            query = query.where(toDateFilter);
        }
        
        if (StringUtils.hasText(transactionType)) {
            BooleanExpression typeFilter = transaction.type.eq(TransactionTypes.valueOf(transactionType));
            query = query.where(typeFilter).orderBy(transaction.createdDate.asc());
        }

        return query.fetch();
    }
}
