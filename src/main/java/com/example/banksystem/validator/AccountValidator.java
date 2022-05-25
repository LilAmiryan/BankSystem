package com.example.banksystem.validator;

import org.springframework.stereotype.Component;

@Component
public class AccountValidator {
    public boolean isValidClientId(Long clientId){
        return true;
    }
}
