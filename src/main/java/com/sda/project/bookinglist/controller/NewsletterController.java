package com.sda.project.bookinglist.controller;

import com.sda.project.bookinglist.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping(value = "/api")
public class NewsletterController {

    @Autowired
    private NewsletterService newsletterService;

    @GetMapping(value = "/newsletter")
    public Response createNewsletter(@RequestParam("email") final String email) {
        newsletterService.insertNewsletter(email);
        return Response.ok().build();
    }

}
