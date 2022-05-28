package com.example.banksystem.controller;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.enumtypeofmodelfields.ErrorType;
import com.example.banksystem.response.client.ClientCreateResponse;
import com.example.banksystem.response.client.ClientDeleteResponse;
import com.example.banksystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) {
        ClientCreateResponse clientCreateResponse = clientService.createClient(clientDto);

        if (clientCreateResponse.getErrorType() != null) {
            return new ClientCreateResponse(clientDto).onFailure();
        }
        return new ClientCreateResponse(clientDto).onSuccess();
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

}
