package com.tricast.managers;

import com.tricast.controllers.requests.AccountRequest;
import com.tricast.controllers.responses.AccountResponse;
import com.tricast.managers.exceptions.SportsbookException;

public interface AccountManager {

    AccountResponse registerNewAccount(AccountRequest accountRequest) throws SportsbookException;

    AccountResponse login(String userName, String password) throws SportsbookException;
}
