package com.sda.project.bookingapp.repository;

import com.sda.project.bookingapp.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPropertyRepository extends JpaRepository<AddressEntity, Long>, JpaSpecificationExecutor {
}
