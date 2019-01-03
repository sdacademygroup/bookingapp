package com.sda.project.bookinglist.repository;

import com.sda.project.bookinglist.entity.NewsletterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsletterRepository extends JpaRepository<NewsletterEntity, Long> {

    Optional<NewsletterEntity> findByEmail(final String email);

}
