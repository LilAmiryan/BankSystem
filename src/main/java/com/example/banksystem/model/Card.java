package com.example.banksystem.model;

import com.example.banksystem.model.enumtypeofmodelfields.BalanceType;
import com.example.banksystem.model.enumtypeofmodelfields.CardStatusType;
import com.example.banksystem.model.enumtypeofmodelfields.CardType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Column(name = "cardType", nullable = false)
    private CardType cardType;    //ENUM

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", foreignKey = @ForeignKey(name = "fk_Bank_Card_ManyToOne"))
    private Bank bank;        //fk_Bank_Card_ManyToOne

    @Column(name = "balanceType", nullable = false)
    private BalanceType balanceType;    //(Default -DEBIT)

    @Column(name = "creditBalance")
    private Double creditBalance;

    @Column(name = "cardNumber", unique = true, nullable = false)
    private String cardNumber;    //unique

    @Column(name = "expireDate", nullable = false)
    private LocalDate expireDate;

    @Column(name = "codeCVC",nullable = false)
    private String codeCVC;

    @Column(name = "status",nullable = false)
    private CardStatusType status;        //ENUM

    @Column(name = "pin",nullable = false)
    private String pin;    //****

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id",foreignKey = @ForeignKey(name ="fk_card_account_oneToOne" ))
    private Account account;    //fk_card_account_oneToOne


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",foreignKey = @ForeignKey(name = "fk_Client_card_ManyToOne"))
    private Client client;        //fk_Client_card_ManyToOne

    public Card() {
    }

    public Card(Long cardId, CardType cardType, Bank bank, BalanceType balanceType, Double creditBalance, String cardNumber, LocalDate expireDate, String codeCVC, CardStatusType status, String pin, Account account, Client client) {
        this.cardId = cardId;
        this.cardType = cardType;
        this.bank = bank;
        this.balanceType = balanceType;
        this.creditBalance = creditBalance;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.codeCVC = codeCVC;
        this.status = status;
        this.pin = pin;
        this.account = account;
        this.client = client;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public Double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(Double creditBalance) {
        this.creditBalance = creditBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getCodeCVC() {
        return codeCVC;
    }

    public void setCodeCVC(String codeCVC) {
        this.codeCVC = codeCVC;
    }

    public CardStatusType getStatus() {
        return status;
    }

    public void setStatus(CardStatusType status) {
        this.status = status;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardId, card.cardId) && cardType == card.cardType && Objects.equals(bank, card.bank) && balanceType == card.balanceType && Objects.equals(creditBalance, card.creditBalance) && Objects.equals(cardNumber, card.cardNumber) && Objects.equals(expireDate, card.expireDate) && Objects.equals(codeCVC, card.codeCVC) && status == card.status && Objects.equals(pin, card.pin) && Objects.equals(account, card.account) && Objects.equals(client, card.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardType, bank, balanceType, creditBalance, cardNumber, expireDate, codeCVC, status, pin, account, client);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardType=" + cardType +
                ", bank=" + bank +
                ", balanceType=" + balanceType +
                ", creditBalance=" + creditBalance +
                ", cardNumber='" + cardNumber + '\'' +
                ", expireDate=" + expireDate +
                ", codeCVC='" + codeCVC + '\'' +
                ", status=" + status +
                ", pin='" + pin + '\'' +
                ", account=" + account +
                ", client=" + client +
                '}';
    }
}
