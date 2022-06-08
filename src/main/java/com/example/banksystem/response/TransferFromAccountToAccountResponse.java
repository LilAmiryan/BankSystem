package com.example.banksystem.response;

import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransferFromAccountToAccountResponse {

    private ErrorType errorType;

    public TransferFromAccountToAccountResponse() {
    }

    public TransferFromAccountToAccountResponse(ErrorType errorType) {
        this.errorType = errorType;
    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input valid account number ");
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No account with such account or card number.");
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
}
