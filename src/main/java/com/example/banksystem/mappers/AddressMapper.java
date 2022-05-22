package com.example.banksystem.mappers;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {
    public Address toAddress(AddressDto addressDto) {
        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setRegion(addressDto.getRegion());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setBuilding(addressDto.getBuilding());
        address.setHome(addressDto.getHome());
        return address;
    }

    public AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCountry(address.getCountry());
        addressDto.setRegion(address.getRegion());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setBuilding(address.getBuilding());
        addressDto.setHome(address.getHome());
        return addressDto;
    }

    public List<AddressDto> mapAllToAddressDto(List<Address> addresses) {
        return addresses.stream().map(this::toAddressDto).collect(Collectors.toList());
    }
}
