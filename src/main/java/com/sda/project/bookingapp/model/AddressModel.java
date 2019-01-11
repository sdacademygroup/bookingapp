package com.sda.project.bookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {

    private Long addressId;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private RoomModel roomModel;
}
