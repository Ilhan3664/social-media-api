package org.example.socialmediaapi.service.impl;

import org.example.socialmediaapi.dto.request.AccountRequest;
import org.example.socialmediaapi.dto.response.AccountResponse;
import org.example.socialmediaapi.entity.Account;
import org.example.socialmediaapi.repository.AccountRepository;
import org.example.socialmediaapi.service.AbstractService;
import org.example.socialmediaapi.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl extends AbstractService implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountResponse getById(Long id) {
        Account account = modelMapper.map(accountRepository.getById(id), Account.class);
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    @Transactional
    public AccountResponse save(AccountRequest request) {
        Account account = modelMapper.map(request, Account.class);
        account.setStatus(1);
        account.setCreateDate(new Date());
        accountRepository.save(account);
        return modelMapper.map(account, AccountResponse.class);
    }

    @Override
    @Transactional
    public AccountResponse update(Long id, AccountRequest newInfo) {
        Account oldAccount = modelMapper.map(accountRepository.getById(id), Account.class);
        Account newAccount = modelMapper.map(newInfo, Account.class);
        oldAccount.setUsername(newAccount.getUsername());
        oldAccount.setPassword(newAccount.getPassword());
        oldAccount.setEmail(newAccount.getEmail());
        oldAccount.setPhone(newAccount.getPhone());
        oldAccount.setUpdateDate(new Date());
        accountRepository.save(oldAccount);
        return modelMapper.map(oldAccount, AccountResponse.class);
    }

    @Override
    @Transactional
    public AccountResponse delete(Long id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.getAll();
    }
}
