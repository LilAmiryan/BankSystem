package com.example.banksystem.dto;

import com.example.banksystem.model.enumtypeofmodelfields.BalanceType;
import com.example.banksystem.model.enumtypeofmodelfields.CardType;
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
    private LocalDate expireDate;
    private String codeCVC;

}
