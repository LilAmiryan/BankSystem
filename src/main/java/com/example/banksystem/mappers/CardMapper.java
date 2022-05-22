package com.example.banksystem.mappers;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public Card toCard(CardDto cardDto){
        Card card=new Card();
        card.setCardType(cardDto.getCardType());
        card.setBalanceType(cardDto.getBalanceType());
        card.setCreditBalance(cardDto.getCreditBalance());
        card.setCardNumber(cardDto.getCardNumber());
        card.setExpireDate(cardDto.getExpireDate());
        card.setCodeCVC(cardDto.getCodeCVC());
        return  card;
    }
    public CardDto toCardDto(Card card){
        CardDto cardDto=new CardDto();
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setCodeCVC(card.getCodeCVC());
        cardDto.setCreditBalance(card.getCreditBalance());
        cardDto.setExpireDate(card.getExpireDate());
        cardDto.setBalanceType(card.getBalanceType());
        return cardDto;
    }
    public List<CardDto> toCardDto(List<Card> cards) {
        return cards.stream().map(this::toCardDto).collect(Collectors.toList());
    }
}
