package com.example.banksystem.response.account;

import com.example.banksystem.dto.AccountDto;
import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountCreateResponse {
    ErrorType errorType;
    private AccountDto accountDto;

    public AccountCreateResponse(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public AccountCreateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No client with such Id.");
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input parameter(s) are wrong.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }

        return response;
    }
    public ResponseEntity<AccountDto> onSuccess() {
        return ResponseEntity.ok().body(accountDto);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
