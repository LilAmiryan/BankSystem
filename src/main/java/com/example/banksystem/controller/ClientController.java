package com.example.banksystem.controller;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.Client;
import com.example.banksystem.response.client.ClientCreateResponse;
import com.example.banksystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if (clientDto == null) {
            return new ClientCreateResponse().onFailure();
        }

        Optional<ClientDto> optionalClientDto = clientService.createClient(clientDto);
        if (optionalClientDto.isEmpty()) {
            return new ClientCreateResponse().onFailure();
        }

        return new ClientCreateResponse(optionalClientDto.get()).onSuccess();
    }


    public Optional<ClientDto> updateClient(ClientDto clientDto, Long id) {

//        if (clientValidator.isValidClient(clientDto)) {
//            if (!clientRepository.existsById(id)) {
//                new Exception("There is no such client");
//            }
//            Client client = clientMapper.toClient(clientDto);
//            client.setClientId(id);
//            Client updatedClient = clientRepository.save(client);
//            return Optional.of(clientMapper.toClientDto(updatedClient));
//        }
        return Optional.empty();

    }
}
