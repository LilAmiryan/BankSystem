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
import com.example.banksystem.response.client.ClientCreateResponse;
import com.example.banksystem.response.client.ClientDeleteResponse;
import com.example.banksystem.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    ClientRepository clientRepository;
    ClientMapper clientMapper;
    ClientValidator clientValidator;
    CardRepository cardRepository;
    AccountRepository accountRepository;
    AddressRepository addressRepository;
    AddressService addressService;
    AddressMapper addressMapper;


    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ClientMapper clientMapper,
                         ClientValidator clientValidator,
                         AddressRepository addressRepository,
                         AddressMapper addressMapper,
                         AddressService addressService,
                         AccountRepository accountRepository,
                         CardRepository cardRepository) {
        this.clientMapper = clientMapper;
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
        this.clientValidator = clientValidator;
        this.addressMapper = addressMapper;
        this.addressService = addressService;
    }

    public ClientCreateResponse createClient(ClientDto clientDto) {
        ErrorType errorType;

        if (!clientValidator.isValidClient(clientDto)) {
            errorType = ErrorType.NOT_VALID;
            return new ClientCreateResponse(errorType);
        }

        if (clientRepository.clientExist(clientMapper.toClient(clientDto))) {
            return new ClientCreateResponse(clientDto);
        }
        AddressDto addressDto = clientDto.getAddressDto();
        Client savedClient=new Client();
        Address address = addressMapper.toAddress(addressDto);


        if (!addressRepository.addressExists(address)) {
            address = addressMapper.toAddress(addressService.createAddress(addressDto).get());
        }

        savedClient = clientMapper.toClient(clientDto);
        savedClient.setAddress(address);

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
}
