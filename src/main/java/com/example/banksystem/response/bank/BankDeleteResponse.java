package com.example.banksystem.response.bank;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.dto.BankDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BankDeleteResponse {
    private BankDto bankDto;

    public BankDeleteResponse() {
    }

    public BankDeleteResponse(BankDto bankDto) {
        this.bankDto = bankDto;
    }

    public ResponseEntity<?> onFailure(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No bank with such id.");
    }

    public ResponseEntity<BankDto> onSuccess(){
        return ResponseEntity.ok().body(bankDto);
    }
}
