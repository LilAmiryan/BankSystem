package com.example.banksystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonProperty("bank")
    private BankDto bankDto;

    @JsonProperty("client")
    private ClientDto clientDto;
    private String accountNumber;
    private Double accountBalance;
    private String iban;

}
