package com.luxury_hotel.room_service.Repository;

import com.luxury_hotel.room_service.model.Amenity;
import com.luxury_hotel.room_service.model.AmenityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(AmenityType name);
    List<Amenity> findByActiveTrue();
}
