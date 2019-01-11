package com.sda.project.bookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel {

    private Long roomId;
    private String roomName;
    private String includes;
    private int maximumPerson;
    private BigDecimal pricePerNight;
    private PropertyModel propertyModel;

}
