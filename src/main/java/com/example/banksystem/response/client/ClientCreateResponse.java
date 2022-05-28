package com.example.banksystem.response.client;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.enums.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientCreateResponse {
    private ClientDto clientDto;
    private ErrorType errorType;

    public ClientCreateResponse(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public ClientCreateResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ClientCreateResponse() {

    }


    public ResponseEntity<?> onFailure(ErrorType errorType) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Input parameter(s) are wrong.");
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public ResponseEntity<ClientDto> onSuccess(ClientDto clientDto) {
        return ResponseEntity.ok().body(clientDto);
    }
}
