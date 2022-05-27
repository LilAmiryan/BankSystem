package com.example.banksystem.service;

import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.mappers.ClientMapper;
import com.example.banksystem.model.Client;
import com.example.banksystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    ClientRepository clientRepository;
    ClientMapper clientMapper;
//    ClientValidator clientValidator;


    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;

    }

    public Optional<ClientDto> createClient(ClientDto clientDto) {
        Client client = clientMapper.toClient(clientDto);
        if (clientRepository.clientExist(client)) {
            return Optional.empty();
        }
        Client savedClient = clientRepository.save(clientMapper.toClient(clientDto));
        return Optional.of(clientMapper.toClientDto(savedClient));
    }

}
