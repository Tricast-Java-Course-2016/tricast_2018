package com.tricast.managers.mappers;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.responses.AccountResponse;
import com.tricast.repositories.entities.Account;

public class AccountMapper {

    public static Account mapToEntity(AccountRequest request) {

        if (request == null) {
            return null;
        }

        Account account = new Account();
        account.setUserName(request.getUserName());
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

    public static AccountResponse mapToReponse(Account entity) {

        if (entity == null) {
            return null;
        }

        AccountResponse account = new AccountResponse();
        account.setId(entity.getId());
        account.setUserName(entity.getUserName());
        account.setFirstName(entity.getFirstName());
        account.setLastName(entity.getLastName());
        account.setDateOfBirth(entity.getDob().toString());
        account.setAddress(entity.getAddress());
        account.setPhoneNumber(entity.getPhoneNumber());
        account.setEmail(entity.getEmail());
        account.setPin(entity.getPin());
        account.setBankAccountNumber(entity.getBankAccountNumber());
        account.setBankCardNumber(entity.getBankCardNumber());
        account.setAccountType(entity.getAccountType());

        return account;
    }
}
