package com.luxury_hotel.room_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "amenities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private AmenityType name;

    @Size(max = 255)
    private String shortDescription;

    @Size(max = 512)
    private String imgUrl;

    @Column(nullable = false)
    private boolean active = true;
}
