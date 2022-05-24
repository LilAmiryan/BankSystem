package com.example.banksystem.service;

import com.example.banksystem.mappers.AccountMapper;
import com.example.banksystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    @Autowired
    AccountService(AccountRepository accountRepository, AccountMapper accountMapper){
        this.accountMapper=accountMapper;
        this.accountRepository=accountRepository;
    }


}
