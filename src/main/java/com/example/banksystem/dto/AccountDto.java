package com.example.banksystem.dto;

import com.example.banksystem.model.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String iban;
    private Bank bank;
    private Double accountBalance;
    private String accountNumber;

}
