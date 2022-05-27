package com.example.banksystem.response.account;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountBalanceIncreaseResponse {

    private ErrorType errorType;
    private AccountDto accountDto;

    public AccountBalanceIncreaseResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AccountBalanceIncreaseResponse(AccountDto accountDto) {
        this.accountDto = accountDto;
    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input valid account number ");
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No account with such account number.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }
        return response;
    }

    public ResponseEntity<?> onSuccess() {
        return ResponseEntity.ok().body("Balance added");
    }


    public ErrorType getErrorType() {
        return errorType;
    }
}
