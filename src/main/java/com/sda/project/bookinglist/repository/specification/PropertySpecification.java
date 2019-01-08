package com.sda.project.bookinglist.repository.specification;

import com.sda.project.bookinglist.entity.AddressEntity;
import com.sda.project.bookinglist.entity.PropertyEntity;
import com.sda.project.bookinglist.entity.RoomEntity;
import com.sda.project.bookinglist.model.SearchPropertyModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class PropertySpecification {

    public static Specification<AddressEntity> prepareSearchPropertyQuery(final SearchPropertyModel searchPropertyModel) {
        return new Specification<AddressEntity>() {

            @Override
            public Predicate toPredicate(Root<AddressEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();
                if (searchPropertyModel.getDestination() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("city"), searchPropertyModel.getDestination()));
                }

                Path<PropertyEntity> propertyEntityPath = root.get("property");
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(propertyEntityPath.get("startsFrom"), searchPropertyModel.getStartsFrom()));

                Path<RoomEntity> roomEntityPath = root.get("room");
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(roomEntityPath.get("maximumPerson"), searchPropertyModel.getAdults() + searchPropertyModel.getChildren()));


                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
