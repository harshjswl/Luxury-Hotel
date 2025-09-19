package com.luxury_hotel.room_service.service.impl;

import com.luxury_hotel.room_service.Mapper.RoomTypeMapper;
import com.luxury_hotel.room_service.Repository.RoomTypeRepository;
import com.luxury_hotel.room_service.Repository.AmenityRepository;
import com.luxury_hotel.room_service.dtos.RequestRoomType;
import com.luxury_hotel.room_service.dtos.ResponseRoomType;
import com.luxury_hotel.room_service.model.Amenity;
import com.luxury_hotel.room_service.model.AmenityType;
import com.luxury_hotel.room_service.model.RoomType;
import com.luxury_hotel.room_service.service.RoomTypeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    @Transactional
    public ResponseRoomType createRoomType(RequestRoomType dto) {
        if (dto.getSlug() == null || dto.getSlug().isBlank()) {
            throw new IllegalArgumentException("Slug must not be blank");
        }

        if (roomTypeRepository.existsBySlug(dto.getSlug())) {
            throw new IllegalArgumentException("RoomType with slug '" + dto.getSlug() + "' already exists");
        }

        Set<Amenity> amenities = mapAmenities(dto.getAmenities());

        RoomType entity = RoomType.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .slug(dto.getSlug())
                .shortDescription(dto.getShortDescription())
                .longDescription(dto.getLongDescription())
                .standardOccupancy(dto.getStandardOccupancy())
                .maxOccupancy(dto.getMaxOccupancy())
                .bedCount(dto.getBedCount())
                .sizeSqMeters(dto.getSizeSqMeters())
                .basePrice(dto.getBasePrice())
                .priceCurrency(dto.getPriceCurrency())
                .extraGuessPrice(dto.getExtraGuestPrice())
                .refundable(dto.isRefundable())
                .defaultImageUrl(dto.getDefaultImageUrl())
                .photoUrls(dto.getPhotoUrls() == null ? new LinkedHashSet<>() : dto.getPhotoUrls())
                .amenities(amenities)
                .active(true)
                .build();

        return RoomTypeMapper.toDTO(roomTypeRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseRoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll()
                .stream()
                .map(RoomTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseRoomType getRoomTypeById(UUID id) {
        RoomType entity = roomTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomType not found with id " + id));
        return RoomTypeMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public ResponseRoomType updateRoomType(UUID id, RequestRoomType dto) {
        RoomType entity = roomTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomType not found with id " + id));

        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setSlug(dto.getSlug());
        entity.setShortDescription(dto.getShortDescription());
        entity.setLongDescription(dto.getLongDescription());
        entity.setStandardOccupancy(dto.getStandardOccupancy());
        entity.setMaxOccupancy(dto.getMaxOccupancy());
        entity.setBedCount(dto.getBedCount());
        entity.setSizeSqMeters(dto.getSizeSqMeters());
        entity.setBasePrice(dto.getBasePrice());
        entity.setPriceCurrency(dto.getPriceCurrency());
        entity.setExtraGuessPrice(dto.getExtraGuestPrice());
        entity.setRefundable(dto.isRefundable());
        entity.setDefaultImageUrl(dto.getDefaultImageUrl());
        entity.setPhotoUrls(dto.getPhotoUrls() == null ? new LinkedHashSet<>() : dto.getPhotoUrls());

        // map and set amenities
        Set<Amenity> amenities = mapAmenities(dto.getAmenities());
        entity.setAmenities(amenities);

        return RoomTypeMapper.toDTO(roomTypeRepository.save(entity));
    }

    @Override
    @Transactional
    public void deleteRoomType(UUID id) {
        RoomType entity = roomTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomType not found with id " + id));
        roomTypeRepository.delete(entity);
    }

    /**
     * Convert incoming amenity strings to Amenity entities.
     * - normalizes user input to match enum names
     * - finds existing Amenity by enum value or creates and saves a new one
     */
    private Set<Amenity> mapAmenities(Set<String> amenityNames) {
        if (amenityNames == null || amenityNames.isEmpty()) {
            return new HashSet<>();
        }
        Set<Amenity> result = new HashSet<>();
        for (String raw : amenityNames) {
            AmenityType type = parseAmenityType(raw);
            if (type == null) {
                // skip unknown amenity names silently (or log if you want)
                continue;
            }
            Amenity amenity = amenityRepository.findByName(type)
                    .orElseGet(() -> {
                        Amenity a = Amenity.builder()
                                .name(type)
                                .shortDescription(type.name())
                                .active(true)
                                .build();
                        return amenityRepository.save(a);
                    });
            result.add(amenity);
        }
        return result;
    }

    /**
     * Normalize and parse a user-provided string into AmenityType.
     * Accepts "air conditioning", "AIR-CONDITIONING", "Air_Conditioning" etc.
     */
    private AmenityType parseAmenityType(String raw) {
        if (raw == null) return null;
        String normalized = raw.trim().toUpperCase()
                .replaceAll("[^A-Z0-9]+", "_")   // non-alphanumeric -> underscore
                .replaceAll("_+", "_")           // collapse multiple underscores
                .replaceAll("^_|_$", "");        // trim leading/trailing underscores
        try {
            return AmenityType.valueOf(normalized);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
