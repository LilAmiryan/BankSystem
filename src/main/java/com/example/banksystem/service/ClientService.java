package com.example.banksystem.service;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.mappers.AddressMapper;
import com.example.banksystem.mappers.ClientMapper;
import com.example.banksystem.model.Address;
import com.example.banksystem.model.Client;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.repository.AccountRepository;
import com.example.banksystem.repository.AddressRepository;
import com.example.banksystem.repository.CardRepository;
import com.example.banksystem.repository.ClientRepository;
import com.example.banksystem.response.address.AddressUpdateResponse;
import com.example.banksystem.response.client.ClientCreateResponse;
import com.example.banksystem.response.client.ClientDeleteResponse;
import com.example.banksystem.response.client.ClientUpdateResponse;
import com.example.banksystem.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    ClientMapper clientMapper;
    ClientValidator clientValidator;
    CardRepository cardRepository;
    AccountRepository accountRepository;
    AddressRepository addressRepository;
    AddressService addressService;
    AddressMapper addressMapper;
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientMapper clientMapper, ClientValidator clientValidator,
                         CardRepository cardRepository, AccountRepository accountRepository,
                         AddressRepository addressRepository, AddressService addressService,
                         AddressMapper addressMapper, ClientRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientValidator = clientValidator;
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
        this.clientRepository = clientRepository;
    }

    public ClientCreateResponse createClient(ClientDto clientDto) {
        ErrorType errorType = null;

        if (!clientValidator.isValidClient(clientDto)) {
            errorType = ErrorType.NOT_VALID;
        }

        Client client = clientMapper.toClient(clientDto);
        if (clientRepository.clientExist(client)) {
            errorType = ErrorType.ALREADY_EXISTS;
        }

        if (errorType != null) {
            return new ClientCreateResponse(errorType);
        }

        AddressDto addressDto = clientDto.getAddressDto();
        Address address = addressMapper.toAddress(addressDto);
        Address savedAddress = addressRepository.save(address);

//        if (!addressRepository.addressExists(address)) {
//            addressDto = addressService.createAddress(addressDto).get();
//            addressRepository.save(addressMapper.toAddress(addressDto));
//        }

        Client savedClient = clientMapper.toClient(clientDto);
        savedClient.setAddress(savedAddress);

        clientRepository.save(savedClient);

        return new ClientCreateResponse(clientMapper.toClientDto(savedClient));
    }

    public ClientDeleteResponse deleteClient(Long id) {
        ErrorType errorType = null;

        if (!clientRepository.existsById(id)) {
            errorType = ErrorType.NOT_FOUND;
        }
        if (cardRepository.existsByClient_clientId(id) || accountRepository.existsByClient_clientId(id)) {
            errorType = ErrorType.ALREADY_EXISTS;
        }

        if (errorType != null) {
            return new ClientDeleteResponse(errorType);
        }
        // ready for delete
        Client deletedClient = clientRepository.findById(id).get();
        clientRepository.deleteById(id);
        return new ClientDeleteResponse(clientMapper.toClientDto(deletedClient));

    }

    public ClientUpdateResponse updateClient(ClientDto clientDto, Long id) {
        ErrorType errorType = null;

        if (!clientValidator.isValidClient(clientDto)) {
            errorType = ErrorType.NOT_VALID;
        }
        if (!clientRepository.existsById(id)) {
            errorType = ErrorType.NOT_FOUND;
        }
        if (errorType != null) {
            return new ClientUpdateResponse(errorType);
        }

        Address address = addressMapper.toAddress(clientDto.getAddressDto());
        addressRepository.save(address);
        Client client = clientMapper.toClient(clientDto);
        client.setClientId(id);
        client.setAddress(address);
        Client updatedClient = clientRepository.save(client);
        return new ClientUpdateResponse(clientMapper.toClientDto(updatedClient));

    }
}
