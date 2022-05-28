package com.example.banksystem.validator;

import com.example.banksystem.dto.ClientDto;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ClientValidator {
    AddressValidator addressValidator;

    public ClientValidator(AddressValidator addressValidator) {
        this.addressValidator = addressValidator;
    }

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9.-]+$");

    public boolean validateEmail(String emailStr) {
        return emailStr.matches(VALID_EMAIL_ADDRESS_REGEX.pattern());
    }

    public boolean isValidClient(ClientDto clientDto) {
        return ((addressValidator.isValidAddress(clientDto.getAddressDto())
                && validateEmail(clientDto.getEmail())
                && !(clientDto.getDateOfBirth() == null
                || clientDto.getFirstName() == null
                || clientDto.getLastName() == null
                || clientDto.getPhoneNumber() == null
                || clientDto.getRegisterDate() == null
                || clientDto.getSsn() == null)));
    }
}