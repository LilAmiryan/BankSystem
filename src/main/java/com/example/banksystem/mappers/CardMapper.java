package com.example.banksystem.mappers;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.model.Card;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardMapper {
    AccountMapper accountMapper;

    public CardMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public Card toCard(CardDto cardDto) {
        Card card = new Card();
        card.setCardType(cardDto.getCardType());
        card.setBalanceType(cardDto.getBalanceType());
        card.setCreditBalance(cardDto.getCreditBalance());
        card.setCardNumber(cardDto.getCardNumber());
        card.setExpireDate(cardDto.getExpireDate());
        card.setCodeCVC(cardDto.getCodeCVC());
        card.setStatus(cardDto.getStatus());
        card.setAccount(accountMapper.toAccount(cardDto.getAccountDto()));
        return card;
    }

    public CardDto toCardDto(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setCodeCVC(card.getCodeCVC());
        cardDto.setCreditBalance(card.getCreditBalance());
        cardDto.setExpireDate(card.getExpireDate());
        cardDto.setBalanceType(card.getBalanceType());
        cardDto.setStatus(card.getStatus());
        cardDto.setAccountDto(accountMapper.toAccountDto(card.getAccount()));
        return cardDto;
    }

    public List<CardDto> toCardDto(List<Card> cards) {
        return cards.stream().map(this::toCardDto).collect(Collectors.toList());
    }
}
