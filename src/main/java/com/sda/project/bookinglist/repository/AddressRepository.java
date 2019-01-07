package com.sda.project.bookinglist.repository;

import com.sda.project.bookinglist.entity.AddressEntity;
import com.sda.project.bookinglist.model.TopDestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query("select new com.sda.project.bookinglist.model.TopDestinationModel(count(a.city),a.city,a.country) from AddressEntity a group by a.city,a.country order by count(a.city) desc")
    List<TopDestinationModel> findTopDestinations();
}
