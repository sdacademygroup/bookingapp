package com.sda.project.bookinglist.repository;

import com.sda.project.bookinglist.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPropertyRepository extends PagingAndSortingRepository<AddressEntity, Long>, JpaSpecificationExecutor {
}
