package com.example.banksystem.response.client;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.dto.ClientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientDeleteResponse {
    private ClientDto clientDto;

    public ClientDeleteResponse() {
    }

    public ClientDeleteResponse(ClientDto clientDto) {
        this.clientDto = clientDto;
    }
    public ResponseEntity<?> onFailure(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No client with such id.");
    }

    public ResponseEntity<ClientDto> onSuccess(){
        return ResponseEntity.ok().body(clientDto);
    }
}

