package com.example.banksystem.response.card;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CardCreateResponse {
    private ErrorType errorType;
    private CardDto cardDto;

    public CardCreateResponse(CardDto cardDto) {
        this.cardDto = cardDto;
    }

    public CardCreateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No client with such Id.");
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input parameter(s) are wrong.");
            case NOT_VALID_ACCOUNT -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No account with such IBAN");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }
        return response;
    }

    public ResponseEntity<CardDto> onSuccess() {
        return ResponseEntity.ok().body(cardDto);
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
