package com.example.banksystem.response.client;
import com.example.banksystem.dto.ClientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClientCreateResponse {
    private ClientDto clientDto;

    public ClientCreateResponse(){}
    public ClientCreateResponse(ClientDto clientDto){
        this.clientDto=clientDto;
    }

    public ResponseEntity<?> onFailure() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Client already exists.");
    }

    public ResponseEntity<ClientDto> onSuccess() {
        return ResponseEntity.ok().body(clientDto);
    }
}
