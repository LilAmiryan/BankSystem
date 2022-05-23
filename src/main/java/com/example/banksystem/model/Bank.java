package com.example.banksystem.model;

import com.example.banksystem.model.enumtypeofmodelfields.BankType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "bank")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "bank_code", length = 30, unique = true, nullable = false)
    private String bankCode;    //	Unique

    @Column(name = "bank_name", length = 30, nullable = false)
    private String bankName;

    @Column(name = "bank_type", length = 30, nullable = false)
    private BankType bankType;    //ENUM

    @OneToMany(mappedBy = "bank", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Account> accounts;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Card> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Bank bank = (Bank) o;
        return bankId != null && Objects.equals(bankId, bank.bankId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
