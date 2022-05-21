package com.example.banksystem.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "country", length = 30, nullable = false)
    private String country;

    @Column(name = "region", length = 30, nullable = false)
    private String region;

    @Column(name = "city", length = 30, nullable = false)
    private String city;

    @Column(name = "street", length = 30, nullable = false)
    private String street;

    @Column(name = "building", length = 30, nullable = true)
    private String building;

    @Column(name = "hime", length = 30, nullable = false)
    private String home;

    public Address() {
    }

    @OneToMany(mappedBy = "address", cascade = CascadeType.PERSIST)
    private List<Client> clients;

    public Address(Long addressId, String country, String region, String city, String street, String building, String home, List<Client> clients) {
        this.addressId = addressId;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.building = building;
        this.home = home;
        this.clients = clients;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) && Objects.equals(country, address.country) && Objects.equals(region, address.region) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(building, address.building) && Objects.equals(home, address.home) && Objects.equals(clients, address.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, country, region, city, street, building, home, clients);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", home='" + home + '\'' +
                ", clients=" + clients +
                '}';
    }
}
