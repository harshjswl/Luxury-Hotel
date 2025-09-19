package com.luxury_hotel.room_service.Repository;

import com.luxury_hotel.room_service.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, UUID> {
    Optional<RoomType> findByCode(String code);
    Optional<RoomType> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
