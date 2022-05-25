package com.example.banksystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "first_name", length = 30, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 30, nullable = false)
    private String lastName;

    @Column(name = "ssn", length = 30, nullable = false, unique = true)
    private String ssn;    //Unique

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "phone_number", length = 30, nullable = false)
    private String phoneNumber;

    @Email(message = "Email must be valid!")
    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",
            foreignKey = @ForeignKey(name = "fk_address_client_ManyToOne"))
    @ToString.Exclude
    private Address address;    //	fk_address_client_ManyToOne

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Card> cards = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Account> accounts = new ArrayList<>();

    @Column(name = "register_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate registerDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return clientId != null && Objects.equals(clientId, client.clientId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
