package com.example.banksystem.mappers;

import com.example.banksystem.dto.BankDto;
import com.example.banksystem.model.Bank;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankMapper {

    public Bank toBank(BankDto bankDto){
        Bank bank=new Bank();
        bank.setBankCode(bankDto.getBankCode());
        bank.setBankName(bankDto.getBankName());
        bank.setBankType(bankDto.getBankType());
        return bank;
    }
    public BankDto toBankDto(Bank bank){
        BankDto bankDto=new BankDto();
        bankDto.setBankCode(bank.getBankCode());
        bankDto.setBankName(bank.getBankName());
        bankDto.setBankType(bank.getBankType());
        return bankDto;
    }
    public List<BankDto> mapAllToBankDto(List<Bank> banks) {
        return banks.stream()
                .map(this::toBankDto)
                .collect(Collectors.toList());
    }

}
