package com.example.banksystem.service;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.mappers.AddressMapper;
import com.example.banksystem.model.Address;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.repository.AddressRepository;
import com.example.banksystem.repository.ClientRepository;
import com.example.banksystem.response.address.AddressDeleteResponse;
import com.example.banksystem.response.address.AddressUpdateResponse;
import com.example.banksystem.validator.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    AddressRepository addressRepository;
    AddressMapper addressMapper;
    AddressValidator addressValidator;

    ClientRepository clientRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper,
                          AddressValidator addressValidator, ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.addressValidator = addressValidator;
        this.clientRepository = clientRepository;
    }

    public Optional<AddressDto> createAddress(AddressDto addressDto) {
        if (addressValidator.isValidAddress(addressDto)) {
            Address address = addressMapper.toAddress(addressDto);
            if (addressRepository.addressExists(address)) {
                return Optional.of(addressMapper.toAddressDto(address));
            }
            Address savedAddress = addressRepository.save(address);
            return Optional.of(addressMapper.toAddressDto(savedAddress));
        }
        return Optional.empty();
    }

    public AddressDeleteResponse deleteAddress(Long id) {
        ErrorType errorType = null;

        if (!addressRepository.existsById(id)) {
            errorType = ErrorType.NOT_FOUND;
        }
        if (clientRepository.existsByAddress_AddressId(id)) {
            errorType = ErrorType.ALREADY_EXISTS;
        }

        if (errorType != null) {
            return new AddressDeleteResponse(errorType);
        }

//      ready for delete
        Address deletedAddress = addressRepository.findById(id).get();
        addressRepository.deleteById(id);

        return new AddressDeleteResponse(addressMapper.toAddressDto(deletedAddress));

    }

    public AddressUpdateResponse updateAddress(AddressDto addressDto, Long id) {
        ErrorType errorType = null;
        if (!addressValidator.isValidAddress(addressDto)) {
            errorType = ErrorType.NOT_VALID;
        }
        if (!addressRepository.existsById(id)) {
            errorType = ErrorType.NOT_FOUND;
        }
        if (errorType != null) {
            return new AddressUpdateResponse(errorType);
        }
        Address address = addressMapper.toAddress(addressDto);
        if (addressRepository.addressExists(address)) {
            addressRepository.delete(address);
            return new AddressUpdateResponse(addressMapper.toAddressDto(address));
        }
        address.setAddressId(id);
        Address savedAddress = addressRepository.save(address);
        return new AddressUpdateResponse(addressMapper.toAddressDto(savedAddress));
    }
}
