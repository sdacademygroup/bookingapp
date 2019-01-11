package com.sda.project.bookingapp.service;

import com.sda.project.bookingapp.converter.SimpleEntityToModelConverter;
import com.sda.project.bookingapp.entity.AddressEntity;
import com.sda.project.bookingapp.entity.PropertyEntity;
import com.sda.project.bookingapp.model.PropertyModel;
import com.sda.project.bookingapp.model.SearchPropertyModel;
import com.sda.project.bookingapp.repository.CustomPropertyRepository;
import com.sda.project.bookingapp.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.sda.project.bookingapp.repository.specification.PropertySpecification.prepareSearchPropertyQuery;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private SimpleEntityToModelConverter simpleEntityToModelConverter;

    @Autowired
    private CustomPropertyRepository customPropertyRepository;

    public PropertyModel getPropertyById(final Long propertyId){

        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);

        return null;
    }

    public List<PropertyModel> getAllProperties() {
        return simpleEntityToModelConverter.propertyEntitiesToModels(propertyRepository.findAll());
    }

    public Page<PropertyModel> getSearchedProperties(final SearchPropertyModel searchPropertyModel) {

        List<AddressEntity> addressEntities = customPropertyRepository
                .findAll(prepareSearchPropertyQuery(searchPropertyModel));

        List<PropertyModel> propertyModels = simpleEntityToModelConverter
                .addressEntitiesToPropertyModels(addressEntities);

        return new PageImpl<>(propertyModels, PageRequest.of(0, 10), propertyModels.size());
    }
}
