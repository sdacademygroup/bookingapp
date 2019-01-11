package com.sda.project.bookingapp.controller;

import com.sda.project.bookingapp.model.PropertyModel;
import com.sda.project.bookingapp.model.SearchPropertyModel;
import com.sda.project.bookingapp.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/api")
public class PropertyRestController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping(value = "/property/search")
    public Page<PropertyModel> getProperties() {
        return propertyService.getSearchedProperties(SearchPropertyModel.builder().adults(1).startsFrom(new BigDecimal(90.00)).children(1).rooms(1).destination("Istanbul").build());
    }
}
