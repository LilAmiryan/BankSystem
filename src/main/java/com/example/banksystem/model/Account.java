package com.example.banksystem.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "iban",length = 30, unique = true, nullable = false)
    private String iban;        //Unique


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", foreignKey = @ForeignKey(name = "fk_Bank_Account_ManyToOne"))
    @ToString.Exclude
    private Bank bank;    //	fk_Bank_Account_ManyToOne

    @Column(name = "account_balance")
    private Double accountBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client_account_ManyToOne"))
    @ToString.Exclude
    private  Client client; //fk_client_account_ManyToOne

    @Column(name = "account_number",length = 40, unique = true, nullable = false)
    private String accountNumber; //Unique

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return accountId != null && Objects.equals(accountId, account.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
