package com.luxury_hotel.room_service.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoomType {
    private UUID id;
    private String code;
    private String name;
    private String slug;
    private String shortDescription;
    private String longDescription;
    private int standardOccupancy;
    private int maxOccupancy;
    private int bedCount;
    private Double sizeSqMeters;
    private BigDecimal basePrice;
    private String priceCurrency;
    private BigDecimal extraGuestPrice;
    private boolean refundable;
    private String defaultImageUrl;
    private Set<String> photoUrls;
    private Set<String> amenities; // amenity names
}
