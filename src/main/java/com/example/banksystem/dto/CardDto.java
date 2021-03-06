package com.example.banksystem.dto;

import com.example.banksystem.model.enums.BalanceType;
import com.example.banksystem.model.enums.CardStatusType;
import com.example.banksystem.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private CardType cardType;
    private BalanceType balanceType;//(Default -DEBIT)
    private Double creditBalance;
    private String cardNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/yyyy")
    private LocalDate expireDate;

    private String codeCVC;
    private CardStatusType status;
    private AccountDto accountDto;
}
