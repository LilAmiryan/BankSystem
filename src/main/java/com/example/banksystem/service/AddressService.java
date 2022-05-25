package com.example.banksystem.service;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.mappers.AddressMapper;
import com.example.banksystem.model.Address;
import com.example.banksystem.repository.AddressRepository;
import com.example.banksystem.repository.ClientRepository;
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

    public Optional<AddressDto> deleteAddress(Long id) throws Exception {
        if (!addressRepository.existsById(id)) {
            throw new Exception("No address with such Id");
        }
        if (clientRepository.existsByAddress_AddressId(id)) {
            return Optional.empty();
        }
        Address deletedAddress = addressRepository.findById(id).get();
                addressRepository.deleteById(id);
        return Optional.of(addressMapper.
                toAddressDto(deletedAddress));

    }

    public Optional<AddressDto> updateAddress(AddressDto addressDto, Long id) throws Exception {


        if (addressValidator.isValidAddress(addressDto)) {
            if (!addressRepository.existsById(id)) {
                throw new Exception("No address with such Id");
            }
            Address address = addressMapper.toAddress(addressDto);
            address.setAddressId(id);
            Address savedAddress = addressRepository.save(address);
            return Optional.of(addressMapper.toAddressDto(savedAddress));

        }
        return Optional.empty();
    }
}
