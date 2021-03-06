package com.example.banksystem.model;

import com.example.banksystem.model.enums.BalanceType;
import com.example.banksystem.model.enums.CardStatusType;
import com.example.banksystem.model.enums.CardType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType cardType;    //ENUM

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", foreignKey = @ForeignKey(name = "fk_Bank_Card_ManyToOne"))
    private Bank bank;        //fk_Bank_Card_ManyToOne

    @Enumerated(EnumType.STRING)
    @Column(name = "balance_type", nullable = false)
    private BalanceType balanceType;    //(Default -DEBIT)

    @Column(name = "credit_balance")
    private Double creditBalance;


    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;
    @Column(name = "expire_date", nullable = false)
    private LocalDate expireDate;

    @Column(name = "code_cvc", nullable = false)
    private String codeCVC;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CardStatusType status;        //ENUM

    @Column(name = "pin", nullable = false)
    private String pin;    //****

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_card_account_oneToOne"))
    private Account account;    //fk_card_account_oneToOne


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_Client_card_ManyToOne"))
    private Client client;        //fk_Client_card_ManyToOne

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return cardId != null && Objects.equals(cardId, card.cardId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
