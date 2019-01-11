package com.sda.project.bookingapp.service;

import com.sda.project.bookingapp.converter.SimpleEntityToModelConverter;
import com.sda.project.bookingapp.entity.NewsletterEntity;
import com.sda.project.bookingapp.model.NewsletterModel;
import com.sda.project.bookingapp.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsletterService {

    @Autowired
    private NewsletterRepository newsletterRepository;

    @Autowired
    private SimpleEntityToModelConverter simpleEntityToModelConverter;

    public void insertNewsletter(final String email) {
        Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findByEmail(email);

        if(!newsletterEntity.isPresent()){
            newsletterRepository.save(NewsletterEntity.builder().email(email).build());
        }
    }

    public NewsletterModel getNewsletterById(final Long id) {
        Optional<NewsletterEntity> newsletterEntity = newsletterRepository.findById(id);
        if (newsletterEntity.isPresent()) {
            return simpleEntityToModelConverter.newsletterEntityToModel(newsletterEntity.get());
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
        List<NewsletterModel> newsletterModels = newsletterEntities.stream().map(simpleEntityToModelConverter::newsletterEntityToModel).collect(Collectors.toList());
        return newsletterModels;
    }
}
