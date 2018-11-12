package com.tricast.managers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tricast.builders.TransactionResponseBuilder;
import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.TransactionRepository;
import com.tricast.repositories.customs.impl.TransactionRepositoryCustomImpl;
import com.tricast.repositories.entities.Account;
import com.tricast.repositories.entities.Transaction;
import com.tricast.repositories.entities.TransactionTypes;

@Service
public class TransactionManagerImpl implements TransactionManager {

    @Autowired
    private TransactionRepositoryCustomImpl transactionRepositoryCustomImpl;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<TransactionResponse> findAll() {
        return null;
    }

    @Override
    public TransactionResponse findById(Long id) {
        return null;
    }

    @Transactional
    @Override
    public TransactionResponse deposit(long accountId, TransactionRequest request) throws SportsbookException {

        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new SportsbookException("Provided account does not exists");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new SportsbookException("Deposite amount must be positive number");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDescription("Feltöltés " + request.getAmount() + "Ft összeggel.");
        transaction.setCreatedDate(OffsetDateTime.now());
        transaction.setType(TransactionTypes.TRANSFER);
        transaction.setAccount(account);

        return TransactionResponseBuilder.build(transactionRepository.save(transaction));
    }

    @Transactional
    @Override
    public TransactionResponse withdraw(long accountId, TransactionRequest request) throws SportsbookException {

        Account account = accountRepository.findById(accountId);

        if (account == null) {
            throw new SportsbookException("Provided account does not exists");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new SportsbookException("Withdrawal amount must be positive number");
        }

        List<Transaction> allTransactions = transactionRepository.findByAccount_id(accountId);
        BigDecimal currentBalance =
                allTransactions.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        if (currentBalance.subtract(request.getAmount()).compareTo(BigDecimal.ZERO) <= 0) {
            throw new SportsbookException("Negativ balance");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount().negate());
        transaction.setDescription("Kiutalás " + request.getAmount() + "Ft összeggel.");
        transaction.setCreatedDate(OffsetDateTime.now());
        transaction.setType(TransactionTypes.WITHDRAWAL);
        transaction.setAccount(account);

        return TransactionResponseBuilder.build(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionResponse> filter(long accountId, String transactionType, OffsetDateTime fromDate,
            OffsetDateTime toDate) {

    	List<Transaction> transactions = transactionRepositoryCustomImpl.filter(transactionType, fromDate, toDate);

		List<TransactionResponse> transactionResponses = new ArrayList<>();
		for (Transaction transaction : transactions) {
			transactionResponses.add(TransactionResponseBuilder.build(transaction));
		}

		return transactionResponses;
	}

}
