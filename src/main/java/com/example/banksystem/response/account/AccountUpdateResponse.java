package com.example.banksystem.response.account;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountUpdateResponse {

    private ErrorType errorType;
    private AccountDto accountDto;

    public AccountUpdateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public AccountUpdateResponse(AccountDto accountDto) {
        this.accountDto = accountDto;
    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input valid account number ");
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No account with such account number.");
            case POSITIVE_BALANCE -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("This account have positive balance.Give him his MONEY.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }
        return response;
    }
    public ResponseEntity<?> onSuccess() {
        return ResponseEntity.ok().body("Account deleted");
    }


    public ErrorType getErrorType() {
        return errorType;
    }
}
