package com.sda.project.bookinglist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchPropertyModel {

    private String destination;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date checkInDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date checkOutDate;

    private int rooms;
    private int adults;
    private int children;

    private BigDecimal startsFrom;

}
