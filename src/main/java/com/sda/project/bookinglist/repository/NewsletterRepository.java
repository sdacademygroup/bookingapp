package com.sda.project.bookinglist.repository;

import com.sda.project.bookinglist.entity.NewsletterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsletterRepository extends CrudRepository<NewsletterEntity, Long> {
}
