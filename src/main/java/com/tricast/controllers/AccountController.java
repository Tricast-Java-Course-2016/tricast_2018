package com.tricast.controllers;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.requests.LoginRequest;
import com.tricast.managers.AccountManager;
import com.tricast.repositories.entities.Account;

@RestController
@RequestMapping(path = "accounts")
public class AccountController {

    private AccountManager accountManager;

    @Inject
    public AccountController(AccountManager playerManager) {
        this.accountManager = playerManager;
    }

    @PostMapping
    public Account create(@RequestBody AccountRequest account) {
        return accountManager.create(account);
    }

    @PutMapping(path = "/{id}")
    public Account update(@PathVariable("id") Long id, @RequestBody AccountRequest account) {
        return accountManager.update(id, account);
    }

    @PostMapping(path = "/login")
    public Account login(@RequestBody LoginRequest loginRequest) {

        return accountManager.login(loginRequest.getUserName(), loginRequest.getPassword());

    }

}
