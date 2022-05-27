package com.example.banksystem.validator;

import com.example.banksystem.model.enumtypeofmodelfields.CardType;
import org.springframework.stereotype.Component;

@Component
public class CardValidator {


//    public boolean isValidClientId(Long clientId) {
//        return true;
//    }

    public boolean isValidCard(CardType cardType) {
        return cardType == CardType.MASTER_CARD ||
                cardType == CardType.VISA ||
                cardType == CardType.ARCA ||
                cardType == CardType.AMERICAN_EXPRESS;
    }
}
