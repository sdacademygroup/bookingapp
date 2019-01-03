package com.sda.project.bookinglist.service;

import com.sda.project.bookinglist.converter.NewsletterEntityToModelConverter;
import com.sda.project.bookinglist.entity.NewsletterEntity;
import com.sda.project.bookinglist.model.NewsletterModel;
import com.sda.project.bookinglist.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Autowired
    private NewsletterEntityToModelConverter newsletterEntityToModelConverter;

    public void insertNewsletter(final String email) {
        Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findByEmail(email);

        if(!newsletterEntity.isPresent()){
            newsletterRepository.save(NewsletterEntity.builder().email(email).build());
        }
    }

    public NewsletterModel getNewsletterById(final Long id) {
        Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findById(id);
        if (newsletterEntity.isPresent()) {
            return newsletterEntityToModelConverter.toModel(newsletterEntity.get());
        } else {
            throw new RuntimeException("Newsletter could not be found");
        }
    }

    public void deleteNewsletterById(final Long id) {
        Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findById(id);
        if (newsletterEntity.isPresent()) {
            newsletterRepository.delete(newsletterEntity.get());
        } else {
            throw new RuntimeException("Newsletter could not be found that you want to delete");
        }
    }

    public void updateNewsletter(final NewsletterModel newsletterModel) {
        Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findById(newsletterModel.getId());
        if (newsletterEntity.isPresent()) {
            newsletterEntity.get().setEmail(newsletterModel.getEmail());
            newsletterRepository.save(newsletterEntity.get());
        } else {
            throw new RuntimeException("Newsletter could not be found that you want to update");
        }
    }

    public List<NewsletterModel> getAllNewsletters() {
        List<NewsletterEntity> newsletterEntities = newsletterRepository.findAll();
        List<NewsletterModel> newsletterModels = newsletterEntities.stream().map(newsletterEntityToModelConverter::toModel).collect(Collectors.toList());
        return newsletterModels;
    }
}
