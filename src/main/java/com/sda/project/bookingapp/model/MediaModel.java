package com.sda.project.bookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaModel {

    private Long mediaId;

    private String mediaUrl;

    private PropertyModel propertyModel;
}
