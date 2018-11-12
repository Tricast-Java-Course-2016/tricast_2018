package com.tricast.managers.mappers;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.repositories.entities.Account;

public class AccountMapper {

    public static Account mapToResponse(AccountRequest request) {
        if (request == null) {
            return null;
        }

        Account account = new Account();
        account.setUserName(request.getUserName());
        account.setPassword(request.getPassword());
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setDob(request.getDob());
        account.setAddress(request.getAddress());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setEmail(request.getEmail());
        account.setPin(request.getPin());
        account.setBankAccountNumber(request.getBankAccountNumber());
        account.setBankCardNumber(request.getBankCardNumber());
        account.setAccountType(request.getAccountType());

        return account;
    }

}
