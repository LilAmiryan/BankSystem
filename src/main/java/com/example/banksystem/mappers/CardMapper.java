package com.example.banksystem.mappers;

import com.example.banksystem.dto.CardDto;
import com.example.banksystem.dto.ClientDto;
import com.example.banksystem.model.Card;
import com.example.banksystem.model.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class CardMapper {
    public Card toCard(CardDto cardDto){
        Card card=new Card();
        card.setCardType(cardDto.getCardType());
        card.setBalanceType(cardDto.getBalanceType());
        card.setCreditBalance(cardDto.getCreditBalance());
//        card.setCardNumber(cardDto.getCardNumber());
        card.setExpireDate(cardDto.getExpireDate());
        card.setCodeCVC(cardDto.getCodeCVC());
        card.setStatus(cardDto.getStatus());
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
        cardDto.setStatus(card.getStatus());
        return cardDto;
    }
    public List<CardDto> toCardDto(List<Card> cards) {
        return cards.stream().map(this::toCardDto).collect(Collectors.toList());
    }
}
