package com.example.banksystem.controller;

import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.response.TransferFromAccountToAccountResponse;
import com.example.banksystem.response.account.AccountBalanceDecreaseResponse;
import com.example.banksystem.response.account.AccountBalanceIncreaseResponse;
import com.example.banksystem.response.account.AccountCreateResponse;
import com.example.banksystem.response.account.AccountDeleteResponse;
import com.example.banksystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PutMapping("/transferAccountAccount")
    public ResponseEntity<?> transferFromAccountToAccount(@RequestBody Double amount,
                                                       @RequestBody String fromAccountNumber,
                                                       @RequestBody String toAccountNumber) {
        TransferFromAccountToAccountResponse transferResponse = accountService.
                transferFromAccountToAccount(amount, fromAccountNumber, toAccountNumber);
        ErrorType errorType = transferResponse.getErrorType();
        if (errorType != null) {
            return transferResponse.onFailure(errorType);
        }
        return transferResponse.onSuccess();
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
    public ResponseEntity<?> deleteAccount(@PathVariable("accountNumber") String accountNumber) {
        AccountDeleteResponse deleteResponse =
                accountService.deleteAccount(accountNumber);

        ErrorType deleteErrorType = deleteResponse.getErrorType();
        if (deleteErrorType != null) {
            return deleteResponse.onFailure(deleteErrorType);
        }
        return deleteResponse.onSuccess();
    }

    @PutMapping("/increaseBalance/{accountNumber}")
    public ResponseEntity<?> increaseBalance(@RequestParam Double sum, @PathVariable String accountNumber) {
        AccountBalanceIncreaseResponse increaseResponse =
                accountService.increaseBalance(sum, accountNumber);

        ErrorType increaseErrorType = increaseResponse.getErrorType();
        if (increaseErrorType != null) {
            return increaseResponse.onFailure(increaseErrorType);
        }
        return increaseResponse.onSuccess();
    }

    @PutMapping("/decreaseBalance/{accountNumber}")
    public ResponseEntity<?> decreaseBalance(@RequestParam Double sum, @PathVariable String accountNumber) {
        AccountBalanceDecreaseResponse decreaseResponse =
                accountService.decreaseBalance(sum, accountNumber);

        ErrorType increaseErrorType = decreaseResponse.getErrorType();
        if (increaseErrorType != null) {
            return decreaseResponse.onFailure(increaseErrorType);
        }
        return decreaseResponse.onSuccess();
    }
}
