package com.example.banksystem.dto;

import com.example.banksystem.model.enums.BankType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
    private String bankCode;
    private String bankName;
    private BankType bankType;

}
