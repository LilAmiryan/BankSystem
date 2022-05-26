package com.example.banksystem.controller;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import com.example.banksystem.response.account.AccountCreateResponse;
import com.example.banksystem.response.address.AddressCreateResponse;
import com.example.banksystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
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

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<?> deleteAddress(@PathVariable("accountNumber") String accountNumber) {
        AccountDeleteResponse deleteResponse =
                accountService.deleteAccount(accountNumber);

        ErrorType deleteErrorType = deleteResponse.getErrorType();
        if (deleteErrorType != null) {
            return deleteResponse.onFailure(deleteErrorType);
        }
        return deleteResponse.onSuccess();
    }
}
