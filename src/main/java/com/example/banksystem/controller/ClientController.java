package com.example.banksystem.controller;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.response.client.ClientCreateResponse;
import com.example.banksystem.response.client.ClientDeleteResponse;
import com.example.banksystem.response.client.ClientUpdateResponse;
import com.example.banksystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

        if (clientDto == null) {
            return new ClientCreateResponse().onFailure();
        }

        Optional<ClientDto> optionalClientDto = clientService.createClient(clientDto);
        if (optionalClientDto.isEmpty()) {
            return new ClientCreateResponse().onFailure();
        }

        return new ClientCreateResponse(optionalClientDto.get()).onSuccess();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@RequestBody ClientDto clientDto,
                                           @PathVariable("id") Long id) {
        Optional<ClientDto> updatedClientDto = clientService.updateClient(clientDto,id);

        if (updatedClientDto.isEmpty()) {
            return new ClientUpdateResponse().onFailure();
        }

        return new ClientUpdateResponse(updatedClientDto.get()).onSuccess();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClientById(@PathVariable("id") Long id) throws Exception {
        Optional <ClientDto> clientDto=clientService.deleteClient(id);
        if (clientDto.isEmpty()) {
            return new ClientUpdateResponse().onFailure();
        }

        return new ClientDeleteResponse(clientDto.get()).onSuccess();
    }
}
