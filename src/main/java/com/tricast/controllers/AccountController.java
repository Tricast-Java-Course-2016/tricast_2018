package com.tricast.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public List<Account> findAll() {
        return accountManager.findAll();
    }

    @PostMapping
    public Account create(@RequestBody Account player) {
        return accountManager.create(player);
    }

}
