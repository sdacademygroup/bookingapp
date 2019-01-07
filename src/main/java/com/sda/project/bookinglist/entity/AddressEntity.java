package com.sda.project.bookinglist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column
    private String street;

    @Column
    private String postalCode;

    @Column
    private String city;

    @Column
    private String country;

    @ManyToOne(targetEntity = PropertyEntity.class)
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private PropertyEntity property;

}
