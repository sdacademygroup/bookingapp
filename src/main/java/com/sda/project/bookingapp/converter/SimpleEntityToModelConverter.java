package com.sda.project.bookingapp.converter;

import com.sda.project.bookingapp.entity.AddressEntity;
import com.sda.project.bookingapp.entity.NewsletterEntity;
import com.sda.project.bookingapp.entity.PropertyEntity;
import com.sda.project.bookingapp.entity.RoomEntity;
import com.sda.project.bookingapp.model.AddressModel;
import com.sda.project.bookingapp.model.NewsletterModel;
import com.sda.project.bookingapp.model.PropertyModel;
import com.sda.project.bookingapp.model.RoomModel;
import com.sda.project.bookingapp.utility.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
            for (RoomEntity roomEntity : propertyEntity.getRooms()) {
                addressModels.add(AddressModel.builder().addressId(roomEntity.getAddress().getAddressId())
                        .street(roomEntity.getAddress().getStreet()).postalCode(roomEntity.getAddress().getPostalCode())
                        .city(roomEntity.getAddress().getCity()).country(roomEntity.getAddress().getCountry()).build());
            }
            propertyModels.add(PropertyModel.builder().addresses(addressModels).propertyId(propertyEntity
                    .getPropertyId()).startsFrom(propertyEntity.getStartsFrom())
                    .propertyName(propertyEntity.getPropertyName()).build());
        }
        return propertyModels;
    }

    public PropertyModel propertyEntityToModel(final PropertyEntity propertyEntity) {

        PropertyModel propertyModel = new PropertyModel();

        propertyModel.setPropertyId(propertyEntity.getPropertyId());

        if (propertyEntity.getAmenities() != null) {
            List<String> amenities = StringUtils.splitStringByComma(propertyEntity.getAmenities());
            propertyModel.setAmenities(amenities);
        }

        propertyModel.setResultPageImageUrl(propertyEntity.getResultPageImageUrl());

        //TODO create MediaEntity and convert multiple media entity to converter
        //propertyModel.setMediaLinks(propertyEntity.getMediaLinks());

        propertyModel.setPropertyDescription(propertyEntity.getPropertyDescription());
        propertyModel.setStartsFrom(propertyEntity.getStartsFrom());


        List<RoomModel> roomModels = new ArrayList<>();
        for (RoomEntity roomEntity : propertyEntity.getRooms()) {
            RoomModel roomModel = new RoomModel();
            roomModel.setRoomId(roomEntity.getRoomId());
            roomModel.setRoomName(roomEntity.getRoomName());
            roomModel.setIncludes(roomEntity.getIncludes());
            roomModel.setMaximumPerson(roomEntity.getMaximumPerson());
            roomModel.setPricePerNight(roomEntity.getPricePerNight());

            roomModels.add(roomModel);
        }

        propertyModel.setRooms(roomModels);

        return propertyModel;
    }


    public List<PropertyModel> addressEntitiesToPropertyModels(final List<AddressEntity> addressEntities) {
        List<PropertyModel> propertyModels = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntities) {
            PropertyModel propertyModel = new PropertyModel();
            propertyModel.setPropertyId(addressEntity.getRoom().getProperty().getPropertyId());
            propertyModel.setPropertyName(addressEntity.getRoom().getProperty().getPropertyName());
            propertyModel.setStartsFrom(addressEntity.getRoom().getProperty().getStartsFrom());
            propertyModel.setPropertyDescription(addressEntity.getRoom().getProperty().getPropertyDescription());

            if (addressEntity.getRoom().getProperty().getAmenities() != null) {
                List<String> amenities = StringUtils.splitStringByComma(addressEntity.getRoom().getProperty().getAmenities());
                propertyModel.setAmenities(amenities);
            }


            AddressModel addressModel = new AddressModel();
            addressModel.setAddressId(addressEntity.getAddressId());
            addressModel.setCity(addressEntity.getCity());
            addressModel.setCountry(addressEntity.getCountry());
            addressModel.setPostalCode(addressEntity.getPostalCode());
            addressModel.setStreet(addressEntity.getStreet());
            propertyModel.getAddresses().add(addressModel);

            propertyModels.add(propertyModel);
        }
        return propertyModels.stream().filter(distinctBy(p -> p.getPropertyName())).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctBy(Function<? super T, ?> f) {
        Set<Object> objects = new HashSet<>();
        return t -> objects.add(f.apply(t));
    }
}
