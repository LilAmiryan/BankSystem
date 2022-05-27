package com.example.banksystem.response.account;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountBalanceDecreaseResponse {
    private ErrorType errorType;
    private AccountDto accountDto;

    public AccountBalanceDecreaseResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AccountBalanceDecreaseResponse(AccountDto accountDto) {
        this.accountDto = accountDto;
    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input valid account number ");
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No account with such account number.");
            case INSUFFICIENT_BALANCE -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Insufficient balance.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }
        return response;
    }

    public ResponseEntity<?> onSuccess() {
        return ResponseEntity.ok().body("Success");
    }


    public ErrorType getErrorType() {
        return errorType;
    }

    public AccountDto getAccountDto() {
        return accountDto;
    }
}
