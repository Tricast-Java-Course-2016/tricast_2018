package com.tricast.controllers;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tricast.controllers.requests.TransactionRequest;
import com.tricast.controllers.responses.TransactionResponse;
import com.tricast.managers.TransactionManager;

@RestController
@RequestMapping(path = "/")
public class TransactionController {

    @Autowired
    private TransactionManager transactionManager;

    @GetMapping(path = "/accounts/{pathAccountId}/transactions/filter")
    public List<TransactionResponse> byFilter(@PathVariable("pathAccountId") Long pathAccountId,
            @RequestParam(value = "transactionType", required = false) String transactionType,
            @RequestParam(value = "fromDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fromDate,
            @RequestParam(value = "toDate", required = true) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime toDate) {

        return this.transactionManager.filter(transactionType, fromDate, toDate);
    }

    @PostMapping("/accounts/{pathAccountId}/transactions/deposit")
    public ResponseEntity<?> depositTransaction(@RequestHeader("headerAccountId") Long headerAccountId,
            @RequestHeader("accountType") String accountType, @PathVariable("pathAccountId") Long pathAccountId,
            @RequestBody TransactionRequest request) {

        // if (!AccountType.valueOf(accountType).equals(AccountType.PLAYER)) {
        // return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        // }

        try {
            TransactionResponse transactionResponse = transactionManager.deposit(pathAccountId, request);
            return ResponseEntity.ok(transactionResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/accounts/{pathAccountId}/transactions/withdraw")
    public ResponseEntity<?> withdrawTransaction(@RequestHeader("headerAccountId") Long headerAccountId,
            @RequestHeader("accountType") String accountType, @PathVariable("pathAccountId") Long pathAccountId,
            @RequestBody TransactionRequest request) {

        // if (!AccountType.valueOf(accountType).equals(AccountType.PLAYER)) {
        // return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Permission denied");
        // }

        try {
            TransactionResponse transactionResponse = transactionManager.withdraw(pathAccountId, request);
            return ResponseEntity.ok(transactionResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
