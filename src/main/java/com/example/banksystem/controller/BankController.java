package com.example.banksystem.controller;

import com.example.banksystem.dto.BankDto;
import com.example.banksystem.model.enums.ErrorType;
import com.example.banksystem.response.bank.BankDeleteResponse;
import com.example.banksystem.response.bank.BankUpdateResponse;
import com.example.banksystem.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // DO NOT USE THIS
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable("id") Long id) {
        Optional<BankDto> bankDto = bankService.deleteBank(id);

        if (bankDto.isEmpty()) {
            return new BankDeleteResponse().onFailure();
        }

        return new BankDeleteResponse(bankDto.get()).onSuccess();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateBank (@RequestBody BankDto bankDto,
                                        @PathVariable("id") Long bankId) {
        BankUpdateResponse updatedResponse = bankService.updateBank(bankDto, bankId);
        ErrorType errorType = updatedResponse.getErrorType();
        if (errorType != null) {
            return updatedResponse.onFailure(errorType);
        }
        return updatedResponse.onSuccess();
    }

}
