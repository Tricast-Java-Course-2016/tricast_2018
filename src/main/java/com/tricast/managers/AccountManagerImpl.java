package com.tricast.managers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.entities.Account;

@Service
public class AccountManagerImpl implements AccountManager {

    private AccountRepository accountRepository;

    @Inject
    public AccountManagerImpl(AccountRepository playerRepository) {
        this.accountRepository = playerRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account create(Account player) {
        return accountRepository.save(player);
    }

    @Override
    public Account update(Account player) {
        return accountRepository.save(player);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.delete(id);
    }

}
