package com.example.banksystem.response.client;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientUpdateResponse {
    private ClientDto clientDto;
    ErrorType errorType;

    public ClientUpdateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ClientUpdateResponse(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public ResponseEntity<?> onFailure(ErrorType errorType) {
        ResponseEntity<?> response;
        switch (errorType){
            case NOT_FOUND -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("No client with such id.");
            case NOT_VALID -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Input parameter(s) are wrong.");
            default -> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Unknown error");
        }
        return response;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public ResponseEntity<ClientDto> onSuccess() {
        return ResponseEntity.ok().body(clientDto);
    }

}
