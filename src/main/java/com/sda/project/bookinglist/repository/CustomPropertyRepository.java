package com.sda.project.bookinglist.repository;

import com.sda.project.bookinglist.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPropertyRepository extends JpaRepository<AddressEntity, Long>, JpaSpecificationExecutor {
}
