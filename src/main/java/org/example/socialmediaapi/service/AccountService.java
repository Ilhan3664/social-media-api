package org.example.socialmediaapi.service;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import java.util.List;

public interface AccountService extends Service<AccountRequest, AccountResponse> {
    List<Account> getAllAccounts();
}