package com.example.banksystem.validator;

import com.example.banksystem.dto.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressValidator {
    public boolean isValidAddress(AddressDto addressDto) {
        return (!(addressDto.getCountry() == null ||
                addressDto.getRegion() == null ||
                addressDto.getCity() == null ||
                addressDto.getStreet() == null ||
                addressDto.getHome() == null));
    }
}
