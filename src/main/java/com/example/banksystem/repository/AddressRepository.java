package com.example.banksystem.repository;

import com.example.banksystem.dto.AddressDto;
import com.example.banksystem.mappers.AddressMapper;
import com.example.banksystem.model.Address;
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
    /*
    default Address existsAddress(AddressDto addressDto){
        AddressMapper addressMapper=null;

        if (addressExists(addressMapper.toAddress(addressDto))){
            return addressMapper.toAddress(addressDto);

        }
    }
*/


}
