package com.tricast.managers;

import java.time.OffsetDateTime;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.responses.AccountResponse;
import com.tricast.managers.exceptions.SportsbookException;
import com.tricast.managers.mappers.AccountMapper;
import com.tricast.repositories.AccountRepository;
import com.tricast.repositories.entities.Account;

@Service
public class AccountManagerImpl implements AccountManager {

    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;

    @Inject
    public AccountManagerImpl(AccountRepository playerRepository, PasswordEncoder encoder) {
        this.accountRepository = playerRepository;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public AccountResponse registerNewAccount(AccountRequest accountRequest) throws SportsbookException {

        validateUsername(accountRequest.getUserName());

        Account account = AccountMapper.mapToEntity(accountRequest);
        account.setPassword(encoder.encode(accountRequest.getPassword()));
        account.setCreatedDate(OffsetDateTime.now());

        return AccountMapper.mapToReponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse login(String username, String password) throws SportsbookException {

        Account foundAccountEntity = accountRepository.findByUserName(username);

        if (foundAccountEntity == null || !encoder.matches(password, foundAccountEntity.getPassword())) {
            throw new SportsbookException("Wrong username or password: " + username);
        }

        return AccountMapper.mapToReponse(foundAccountEntity);
    }

    private void validateUsername(String username) throws SportsbookException {

        Account accountFoundByUsername = accountRepository.findByUserName(username);
        if (accountFoundByUsername != null) {
            throw new SportsbookException("Username is already occupied");
        }
    }
}
