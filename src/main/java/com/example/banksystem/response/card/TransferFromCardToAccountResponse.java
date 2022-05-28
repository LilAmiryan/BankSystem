package com.example.banksystem.response.card;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransferFromCardToAccountResponse {

    private ErrorType errorType;


    public TransferFromCardToAccountResponse() {
    }

    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input valid card or account number ");
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No account or card with such account or card number.");
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

    public TransferFromCardToAccountResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
