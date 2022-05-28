package com.example.banksystem.response.client;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientDeleteResponse {
private ClientDto clientDto;
private ErrorType errorType;

    public ClientDeleteResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ClientDeleteResponse(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType) {
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No client with such Id.");
            case ALREADY_EXISTS -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("This client is in use by another card or account.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Unknown error");
        }

        return response;
    }

    public ResponseEntity<ClientDto> onSuccess() {
        return ResponseEntity.ok().body(clientDto);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
