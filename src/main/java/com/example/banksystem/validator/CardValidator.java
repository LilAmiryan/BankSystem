package com.example.banksystem.validator;

import com.example.banksystem.model.enums.CardType;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CardValidator {

    private static final java.util.regex.Pattern ACCOUNT_NUMBER_PATTERN = Pattern.compile("[0-9]{16}");



//    public boolean isValidClientId(Long clientId) {
//        return true;
//    }

    public boolean isValidCard(CardType cardType) {
        return cardType == CardType.MASTER_CARD ||
                cardType == CardType.VISA ||
                cardType == CardType.ARCA ||
                cardType == CardType.AMERICAN_EXPRESS;
    }


    public boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches(ACCOUNT_NUMBER_PATTERN.pattern());
    }
}
