package com.sda.project.bookingapp.controller;

import com.sda.project.bookingapp.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResultController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/search/{propertyId}")
    public ModelAndView searchPropertyById(@PathVariable("propertyId") Long propertyId) {

        return null;
    }
}
