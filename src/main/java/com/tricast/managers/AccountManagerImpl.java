package com.tricast.managers;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.managers.mappers.AccountMapper;
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
    public Account findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account create(AccountRequest accountRequest) {
        Account account = AccountMapper.mapToResponse(accountRequest);
        // TODO hash the password before save it in the db
        return accountRepository.save(account);
    }

    @Override
    public Account update(long id, AccountRequest accountRequest) {
        Account account = AccountMapper.mapToResponse(accountRequest);
        account.setId(id);
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.delete(id);
    }

    @Override
    public Account login(String username, String password) {
        // TODO add username and password check and decode password
        Account account = accountRepository.findByUserName(username);
        return account;
    }

}
