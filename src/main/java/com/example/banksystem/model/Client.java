package com.example.banksystem.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "firstName", length = 30, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 30, nullable = false)
    private String lastName;

    @Column(name = "ssn", length = 30, nullable = false, unique = true)
    private String ssn;    //Unique

    @Column(name = "dateOfBirth", nullable = false)
    private java.time.LocalDate dateOfBirth;

    @Column(name = "phoneNumber", length = 30, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id",
            foreignKey = @ForeignKey(name = "fk_address_client_ManyToOne"))
    private Address address;    //	fk_address_client_ManyToOne

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Card> cards;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Account> accounts;

    @Column(name = "registerDate", nullable = false)
    private LocalDate registerDate;

    public Client() {
    }

    public Client(Long clientId, String firstName, String lastName, String ssn, LocalDate dateOfBirth, String phoneNumber, String email, Address address, List<Card> cards, List<Account> accounts, LocalDate registerDate) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.cards = cards;
        this.accounts = accounts;
        this.registerDate = registerDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(clientId, client.clientId) && Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(ssn, client.ssn) && Objects.equals(dateOfBirth, client.dateOfBirth) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(email, client.email) && Objects.equals(address, client.address) && Objects.equals(cards, client.cards) && Objects.equals(accounts, client.accounts) && Objects.equals(registerDate, client.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, firstName, lastName, ssn, dateOfBirth, phoneNumber, email, address, cards, accounts, registerDate);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", cards=" + cards +
                ", accounts=" + accounts +
                ", registerDate=" + registerDate +
                '}';
    }
}
