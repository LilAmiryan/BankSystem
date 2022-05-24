package com.example.banksystem.service;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.mappers.ClientMapper;
import com.example.banksystem.model.Client;
import com.example.banksystem.repository.AccountRepository;
import com.example.banksystem.repository.CardRepository;
import com.example.banksystem.repository.ClientRepository;
import com.example.banksystem.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    ClientRepository clientRepository;
    ClientMapper clientMapper;
    ClientValidator clientValidator;
    CardRepository cardRepository;
    AccountRepository accountRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, ClientValidator clientValidator) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.clientValidator = clientValidator;
    }

    public Optional<ClientDto> createClient(ClientDto clientDto) {
        if (clientValidator.isValidClient(clientDto)) {
            Client client = clientMapper.toClient(clientDto);
            if (clientRepository.clientExist(client)) {
                return Optional.of(clientMapper.toClientDto(client));
            }
            Client savedClient = clientRepository.save(client);
            return Optional.of(clientMapper.toClientDto(savedClient));
        }
        return Optional.empty();
    }

    public Optional<ClientDto> deleteClient(Long id) throws Exception {
        if (!(clientRepository.existsById(id))) {
            throw new Exception("No client with such Id");
        }
        if (cardRepository.existsByClient_ClientId(id)) {
            return Optional.empty();
        }
        if (accountRepository.existsByClient_ClientId(id)) {
            return Optional.empty();
        }
        Client deletedClient = clientRepository.findById(id).get();
        clientRepository.deleteById(id);
        return Optional.of(clientMapper.toClientDto(deletedClient));

    }

    public Optional<ClientDto> updateClient(ClientDto clientDto, Long id) {

        if (clientValidator.isValidClient(clientDto)) {
            if (!clientRepository.existsById(id)) {
                new Exception("There is no such client");
            }
            Client client = clientMapper.toClient(clientDto);
            client.setClientId(id);
            Client updatedClient = clientRepository.save(client);
            return Optional.of(clientMapper.toClientDto(updatedClient));
        }
        return Optional.empty();

    }
}
