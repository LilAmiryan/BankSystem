package com.example.banksystem.validator;

import com.example.banksystem.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientValidator {
    public boolean isValidClient(ClientDto clientDto) {
        return (!(clientDto.getAddress().equals(null)
                || clientDto.getDateOfBirth() == null
                || clientDto.getFirstName() == null
                || clientDto.getLastName() == null
                || clientDto.getPhoneNumber() == null
                || clientDto.getRegisterDate() == null
                || clientDto.getSsn() == null));

    }
}
