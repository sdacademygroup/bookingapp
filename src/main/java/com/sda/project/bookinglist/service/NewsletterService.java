package com.sda.project.bookinglist.service;

import com.sda.project.bookinglist.entity.NewsletterEntity;
import com.sda.project.bookinglist.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    public void insertNewsletter(final String email){
        newsletterRepository.save(NewsletterEntity.builder().email(email).build());
    }
}
