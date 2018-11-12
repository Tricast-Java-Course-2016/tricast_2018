package com.tricast.managers;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.repositories.entities.Account;

public interface AccountManager {

    Account findById(Long id);

    Account create(AccountRequest accountRequest);

    Account update(long id, AccountRequest accountRequest);

    Account login(String userName, String password);

    void deleteById(Long id);

}
