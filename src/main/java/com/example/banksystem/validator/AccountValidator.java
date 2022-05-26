package com.example.banksystem.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class AccountValidator {

    private static final java.util.regex.Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("[0-9]{16}");

    public boolean isValidClientId(Long clientId) {
        return true;
    }

    public boolean isValidAccountNumber(String accountNumber) {
        return accountNumber.matches(ACCOUNT_NUMBER_PATTERN.pattern());
    }
}
