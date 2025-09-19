package com.luxury_hotel.room_service.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestRoomType {

    @NotBlank
    @Size(max = 32)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String slug;

    @Size(max = 255)
    private String shortDescription;

    private String longDescription;

    @Min(1)
    private int standardOccupancy = 1;

    @Min(1)
    private int maxOccupancy = 2;

    @Min(1)
    private int bedCount = 1;

    @PositiveOrZero
    private Double sizeSqMeters;

    @DecimalMin("0.0")
    private BigDecimal basePrice;

    @NotBlank
    @Size(max = 8)
    private String priceCurrency = "INR";

    @DecimalMin("0.0")
    private BigDecimal extraGuestPrice;

    private boolean refundable = true;

    @Size(max = 1024)
    private String defaultImageUrl;

    private Set<String> photoUrls;

    private Set<String> amenities; // amenity names (e.g. "WiFi", "Air Conditioning")
}
