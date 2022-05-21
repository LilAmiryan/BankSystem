package com.example.banksystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "iban",length = 30, unique = true, nullable = false)
    private String iban;        //Unique


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", foreignKey = @ForeignKey(name = "fk_Bank_Account_ManyToOne"))
    private Bank bank;    //	fk_Bank_Account_ManyToOne

    @Column(name = "accountBalance")
    private Double accountBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client_account_ManyToOne"))
    private  Client client; //fk_client_account_ManyToOne

    @Column(name = "accountNumber",length = 40, unique = true, nullable = false)
    private String accountNumber; //Unique


    public Account() {
    }

    public Account(Long accountId, String iban, Bank bank, Double accountBalance, Client client, String accountNumber) {
        this.accountId = accountId;
        this.iban = iban;
        this.bank = bank;
        this.accountBalance = accountBalance;
        this.client = client;
        this.accountNumber = accountNumber;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) && Objects.equals(iban, account.iban) && Objects.equals(bank, account.bank) && Objects.equals(accountBalance, account.accountBalance) && Objects.equals(client, account.client) && Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, iban, bank, accountBalance, client, accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", iban='" + iban + '\'' +
                ", bank=" + bank +
                ", accountBalance=" + accountBalance +
                ", client=" + client +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
