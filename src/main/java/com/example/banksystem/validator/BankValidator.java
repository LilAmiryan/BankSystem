package com.example.banksystem.validator;

import com.example.banksystem.dto.BankDto;
import com.example.banksystem.model.enumtypeofmodelfields.BankType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

@Component
public class BankValidator {

    public boolean isValidBank(BankDto bankDto) {
        try {
            return ((bankDto.getBankCode() != null &&
                    bankDto.getBankName() != null) ||
                    bankDto.getBankType().equals(BankType.CENTRAL_BANK) ||
                    bankDto.getBankType().equals(BankType.COOPERATIVE_BANK) ||
                    bankDto.getBankType().equals(BankType.COMMERCIAL_BANK) ||
                    bankDto.getBankType().equals(BankType.SPECIALISED_BANK));
        } catch (HttpMessageNotReadableException ex) {
            System.out.println("try block");
            return false;
        }
    }

}
