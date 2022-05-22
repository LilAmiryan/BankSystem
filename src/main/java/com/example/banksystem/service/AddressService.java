package com.example.banksystem.service;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.mappers.AddressMapper;
import com.example.banksystem.model.Address;
import com.example.banksystem.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class AddressService {
    AddressRepository addressRepository;
    AddressMapper addressMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public Optional<AddressDto> createAddress(AddressDto addressDto) {
        Address address = addressMapper.toAddress(addressDto);
        if (addressRepository.addressExists(address)) {
            return Optional.empty();
        }
        Address savedAddress = addressRepository.save(addressMapper.toAddress(addressDto));

        return Optional.of(
                addressMapper.toAddressDto(savedAddress)
        );
    }

    public Optional<AddressDto> deleteAddress(Long id) {
        try {
            Address address = addressRepository.findById(id).get();
            addressRepository.deleteById(id);
            return Optional.of(addressMapper.toAddressDto(address));
        } catch (EmptyResultDataAccessException | NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    public Optional<AddressDto> updateAddress(AddressDto addressDto, Long id) {
        try {
            addressRepository.findById(id).get();
            Address address = addressMapper.toAddress(addressDto);
            address.setAddressId(id);
            Address savedAddress = addressRepository.save(address);
            return Optional.of(addressMapper.toAddressDto(savedAddress));
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }
}
