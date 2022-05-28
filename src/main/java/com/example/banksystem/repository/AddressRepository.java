package com.example.banksystem.repository;

import com.example.banksystem.model.Address;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    boolean existsByCountryAndRegionAndCityAndStreetAndBuildingAndHome
            (String country, String region, String city,
             String street, String building, String home);

    default boolean addressExists(Address address) {
        return
                existsByCountryAndRegionAndCityAndStreetAndBuildingAndHome(
                        address.getCountry(),
                        address.getRegion(),
                        address.getCity(),
                        address.getStreet(),
                        address.getBuilding(),
                        address.getHome());
    }



}
