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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
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

            AddressModel addressModel = new AddressModel();
            addressModel.setAddressId(addressEntity.getAddressId());
            addressModel.setCity(addressEntity.getCity());
            addressModel.setCountry(addressEntity.getCountry());
            addressModel.setPostalCode(addressEntity.getCountry());
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
