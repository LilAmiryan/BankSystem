package com.example.banksystem.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
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

    @Column(name = "home", length = 30, nullable = false)
    private String home;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return addressId != null && Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
