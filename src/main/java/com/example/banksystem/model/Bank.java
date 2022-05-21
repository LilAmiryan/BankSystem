package com.example.banksystem.model;

import com.example.banksystem.model.enumtypeofmodelfields.BankType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "bankCode", length = 30, unique = true, nullable = false)
    private String bankCode;    //	Unique

    @Column(name = "bankName", length = 30, nullable = false)
    private String bankName;

    @Column(name = "bankType", length = 30, nullable = false)
    private BankType bankType;    //ENUM

    @OneToMany(mappedBy = "bank", cascade = CascadeType.PERSIST)
    private List<Account> accounts;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.PERSIST)
    private List<Card> cards;

    public Bank() {
    }

    public Bank(Long bankId, String bankCode, String bankName, BankType bankType, List<Account> accounts, List<Card> cards) {
        this.bankId = bankId;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.bankType = bankType;
        this.accounts = accounts;
        this.cards = cards;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BankType getBankType() {
        return bankType;
    }

    public void setBankType(BankType bankType) {
        this.bankType = bankType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(bankId, bank.bankId) && Objects.equals(bankCode, bank.bankCode) && Objects.equals(bankName, bank.bankName) && bankType == bank.bankType && Objects.equals(accounts, bank.accounts) && Objects.equals(cards, bank.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId, bankCode, bankName, bankType, accounts, cards);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", bankCode='" + bankCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankType=" + bankType +
                ", accounts=" + accounts +
                ", cards=" + cards +
                '}';
    }
}
