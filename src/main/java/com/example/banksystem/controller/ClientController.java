package com.example.banksystem.controller;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.response.client.ClientCreateResponse;
import com.example.banksystem.response.client.ClientDeleteResponse;
import com.example.banksystem.response.client.ClientUpdateResponse;
import com.example.banksystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) {
        ClientCreateResponse clientCreateResponse = clientService.createClient(clientDto);
        ErrorType errorType = clientCreateResponse.getErrorType();

        if (clientCreateResponse.getErrorType() != null) {
            return new ClientCreateResponse().onFailure(errorType);
        }
        return clientCreateResponse.onSuccess(clientDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
        ClientDeleteResponse deleteResponse = clientService.deleteClient(id);
        ErrorType deleteErrorType = deleteResponse.getErrorType();
        if (deleteErrorType != null) {
            return deleteResponse.onFailure(deleteErrorType);
        }
        return deleteResponse.onSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto, @PathVariable("id") Long id) {
        ClientUpdateResponse clientUpdateResponse = clientService.updateClient(clientDto, id);
        ErrorType errorType = clientUpdateResponse.getErrorType();
        if (errorType != null) {
            return clientUpdateResponse.onFailure(errorType);
        }
        return clientUpdateResponse.onSuccess();
    }
}
