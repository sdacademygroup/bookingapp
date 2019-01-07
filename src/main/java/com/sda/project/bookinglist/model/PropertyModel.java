package com.sda.project.bookinglist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {

    private Long propertyId;

    private String propertyName;

    private BigDecimal startsFrom;

    private List<AddressModel> addresses;
}
