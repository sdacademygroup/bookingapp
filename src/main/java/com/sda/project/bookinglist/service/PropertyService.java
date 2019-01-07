package com.sda.project.bookinglist.service;

import com.sda.project.bookinglist.entity.AddressEntity;
import com.sda.project.bookinglist.entity.PropertyEntity;
import com.sda.project.bookinglist.model.AddressModel;
import com.sda.project.bookinglist.model.PropertyModel;
import com.sda.project.bookinglist.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<PropertyModel> getProperties() {

        List<PropertyEntity> propertyEntities = propertyRepository.findAll();

        List<PropertyModel> propertyModels = new ArrayList<>();
        for (PropertyEntity propertyEntity : propertyEntities) {
            List<AddressModel> addressModels = new ArrayList<>();
            for (AddressEntity addressEntity : propertyEntity.getAddresses()) {
                addressModels.add(AddressModel.builder().addressId(addressEntity.getAddressId()).street(addressEntity.getStreet()).postalCode(addressEntity.getPostalCode()).city(addressEntity.getCity()).country(addressEntity.getCountry()).build());
            }
            propertyModels.add(PropertyModel.builder().addresses(addressModels).propertyId(propertyEntity.getPropertyId()).startsFrom(propertyEntity.getStartsFrom()).propertyName(propertyEntity.getPropertyName()).build());
        }

        return propertyModels;
    }

}
