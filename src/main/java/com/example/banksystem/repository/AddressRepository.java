package com.example.banksystem.repository;

import com.example.banksystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
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
