package com.example.banksystem.controller;

import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import com.example.banksystem.response.account.AccountCreateResponse;
import com.example.banksystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createAccount(@PathVariable("id") Long clientId) {

        AccountCreateResponse accountCreateResponse = accountService.createAccount(clientId);
        ErrorType errorType = accountCreateResponse.getErrorType();

        if (errorType != null) {
            return accountCreateResponse.onFailure(errorType);
        }
        return accountCreateResponse.onSuccess();
    }
}
