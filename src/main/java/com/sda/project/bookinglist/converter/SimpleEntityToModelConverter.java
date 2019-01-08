package com.sda.project.bookinglist.converter;

import com.sda.project.bookinglist.entity.AddressEntity;
import com.sda.project.bookinglist.entity.NewsletterEntity;
import com.sda.project.bookinglist.entity.PropertyEntity;
import com.sda.project.bookinglist.model.AddressModel;
import com.sda.project.bookinglist.model.NewsletterModel;
import com.sda.project.bookinglist.model.PropertyModel;
import com.sda.project.bookinglist.model.RoomModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleEntityToModelConverter {

    public NewsletterModel newsletterEntityToModel(final NewsletterEntity newsletterEntity) {
        NewsletterModel newsletterModel = new NewsletterModel();
        newsletterModel.setId(newsletterEntity.getId());
        newsletterModel.setCreatedAt(newsletterEntity.getCreatedAt());
        newsletterModel.setEmail(newsletterEntity.getEmail());
        return newsletterModel;
    }

    public List<PropertyModel> propertyEntitiesToModels(final List<PropertyEntity> propertyEntities) {
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

    public List<PropertyModel> addressEntitiesToPropertyModels(final List<AddressEntity> addressEntities) {
        List<PropertyModel> propertyModels = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntities) {
            PropertyModel propertyModel = new PropertyModel();
            propertyModel.setPropertyId(addressEntity.getProperty().getPropertyId());
            propertyModel.setPropertyName(addressEntity.getProperty().getPropertyName());
            propertyModel.setStartsFrom(addressEntity.getProperty().getStartsFrom());

            RoomModel roomModel = new RoomModel();
            roomModel.setRoomId(addressEntity.getRoom().getRoomId());
            roomModel.setRoomName(addressEntity.getRoom().getRoomName());
            roomModel.setIncludes(addressEntity.getRoom().getIncludes());
            roomModel.setMaximumPerson(addressEntity.getRoom().getMaximumPerson());
            roomModel.setPricePerNight(addressEntity.getRoom().getPricePerNight());
            propertyModel.getRooms().add(roomModel);

            AddressModel addressModel = new AddressModel();
            addressModel.setAddressId(addressEntity.getAddressId());
            addressModel.setCity(addressEntity.getCity());
            addressModel.setCountry(addressEntity.getCountry());
            addressModel.setPostalCode(addressEntity.getCountry());
            addressModel.setStreet(addressEntity.getStreet());
            propertyModel.getAddresses().add(addressModel);

            propertyModels.add(propertyModel);
        }
        return propertyModels;
    }
}
