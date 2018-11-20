package com.tricast.controllers;

import java.time.OffsetDateTime;
import java.util.Date;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tricast.controllers.filters.AuthenticationSettings;
import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.requests.LoginRequest;
import com.tricast.controllers.responses.AccountBalanceResponse;
import com.tricast.controllers.responses.AccountResponse;
import com.tricast.managers.AccountManager;
import com.tricast.repositories.entities.AccountType;

@RestController
@RequestMapping(path = "api/accounts")
public class AccountController {

    private static final Logger LOG = LogManager.getLogger(AccountController.class);

    private AccountManager accountManager;

    @Inject
    public AccountController(AccountManager playerManager) {
        this.accountManager = playerManager;
    }

    @PostMapping
    public ResponseEntity<?> registerAccount(@RequestBody AccountRequest accountRequest) {

        LOG.info("Trying to create or update account for " + accountRequest.getFirstName() + " "
                + accountRequest.getLastName());

        try {
            AccountResponse accountResponse = accountManager.registerNewAccount(accountRequest);
            return ResponseEntity.ok(accountResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        LOG.info("Trying to login with account for " + loginRequest.getUserName());

        try {
            AccountResponse account = accountManager.login(loginRequest.getUserName(), loginRequest.getPassword());

            String token = issueToken(account.getId(), account.getUserName(), account.getAccountType());

            HttpHeaders header = new HttpHeaders();
            header.add("Authorization", "Bearer " + token);

            return ResponseEntity.ok().headers(header).body(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private String issueToken(long accountId, String username, AccountType accountType) {
        OffsetDateTime exp = OffsetDateTime.now().plusHours(6);
        Algorithm algorithm = Algorithm.HMAC256(AuthenticationSettings.SECRET_KEY);

        return JWT.create().withIssuer(AuthenticationSettings.ISSUER).withExpiresAt(Date.from(exp.toInstant()))
                .withClaim(AuthenticationSettings.CLAIM_ACCOUNTID_IDENTIFIER, accountId)
                .withClaim(AuthenticationSettings.CLAIM_USERNAME_IDENTIFIER, username)
                .withClaim(AuthenticationSettings.CLAIM_ACCOUNTTYPE_IDENTIFIER, accountType.name()).sign(algorithm);
    }

    @GetMapping(path = "/{accountId}/balance")
    public ResponseEntity<?> getBalance(@RequestAttribute("authentication.accountType") String accountType,
            @RequestAttribute("authentication.accountId") Long attributeAccountId,
            @PathVariable("accountId") Long accountId) {

        if (!AccountType.valueOf(accountType).equals(AccountType.PLAYER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        }

        if (!attributeAccountId.equals(accountId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided account id and the token not match.");
        }

        LOG.info("Get balance for " + accountId);

        try {
            return ResponseEntity.ok().body(new AccountBalanceResponse(accountManager.getBalance(accountId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
