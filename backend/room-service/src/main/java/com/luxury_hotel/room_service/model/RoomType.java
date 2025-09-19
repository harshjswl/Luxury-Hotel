package com.luxury_hotel.room_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "room_type",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_room_type_code", columnNames = {"code"}),
                @UniqueConstraint(name = "uk_room_type_slug", columnNames = {"slug"})
        },
        indexes = {
                @Index(name = "idx_room_type_name", columnList = "name"),
                @Index(name = "idx_room_type_active", columnList = "active")
        }
)
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 32)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 120)
    private String slug;

    private String shortDescription;

    @Column(columnDefinition = "text")
    private String longDescription;

    @Builder.Default
    private int standardOccupancy = 1;

    @Builder.Default
    private int maxOccupancy = 2;

    @Builder.Default
    private int bedCount = 1;

    @PositiveOrZero
    private Double sizeSqMeters;

    @Builder.Default
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal basePrice = BigDecimal.ZERO;

    @Builder.Default
    private String priceCurrency = "INR";

    @Builder.Default
    @Column(precision = 12, scale = 2)
    private BigDecimal extraGuessPrice = BigDecimal.ZERO;

    @Builder.Default
    @Column(nullable = false)
    private boolean refundable = true;

    private String defaultImageUrl;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "room_type_photos", joinColumns = @JoinColumn(name = "room_type_id"))
    @Column(name = "photo_url", length = 1024)
    private Set<String> photoUrls = new LinkedHashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "room_type_amenities",
            joinColumns = @JoinColumn(name = "room_type_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"),
            indexes = {
                    @Index(name = "idx_rt_amenity_rt", columnList = "room_type_id"),
                    @Index(name = "idx_rt_amenity_a", columnList = "amenity_id")
            }
    )
    private Set<Amenity> amenities = new HashSet<>();

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addPhoto(String url) {
        if (url != null) this.photoUrls.add(url);
    }

    public void addAmenity(Amenity amenity) {
        if (amenity != null) this.amenities.add(amenity);
    }
}
