package com.luxury_hotel.room_service.Mapper;

import com.luxury_hotel.room_service.dtos.ResponseRoomType;
import com.luxury_hotel.room_service.model.RoomType;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomTypeMapper {

    public static ResponseRoomType toDTO(RoomType entity) {
        return ResponseRoomType.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .slug(entity.getSlug())
                .shortDescription(entity.getShortDescription())
                .longDescription(entity.getLongDescription())
                .standardOccupancy(entity.getStandardOccupancy())
                .maxOccupancy(entity.getMaxOccupancy())
                .bedCount(entity.getBedCount())
                .sizeSqMeters(entity.getSizeSqMeters())
                .basePrice(entity.getBasePrice())
                .priceCurrency(entity.getPriceCurrency())
                .extraGuestPrice(entity.getExtraGuessPrice())
                .refundable(entity.isRefundable())
                .defaultImageUrl(entity.getDefaultImageUrl())
                .photoUrls(entity.getPhotoUrls() == null ? Collections.emptySet() : entity.getPhotoUrls())
                .amenities(
                        entity.getAmenities() == null
                                ? Collections.emptySet()
                                : entity.getAmenities()
                                .stream()
                                .map(a -> a.getName() == null ? null : a.getName().toString())
                                .filter(Objects::nonNull)
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
