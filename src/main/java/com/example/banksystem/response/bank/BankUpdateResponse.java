package com.example.banksystem.response.bank;

import com.example.banksystem.dto.BankDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BankUpdateResponse {
    private BankDto bankDto;
    private ErrorType errorType;

    public BankUpdateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BankUpdateResponse(BankDto bankDto) {
        this.bankDto = bankDto;
    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input parameter(s) are wrong.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }
        return response;
    }

    public ResponseEntity<BankDto> onSuccess() {
        return ResponseEntity.ok().body(bankDto);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
