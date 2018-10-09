package com.tricast.managers;

import java.util.List;

import com.tricast.repositories.entities.Account;

public interface AccountManager {

    List<Account> findAll();

    Account findById(Long id);

    Account create(Account player);

    Account update(Account player);

    void deleteById(Long id);

}
