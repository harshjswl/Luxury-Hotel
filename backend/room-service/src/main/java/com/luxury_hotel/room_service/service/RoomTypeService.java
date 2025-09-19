package com.luxury_hotel.room_service.service;

import com.luxury_hotel.room_service.dtos.RequestRoomType;
import com.luxury_hotel.room_service.dtos.ResponseRoomType;

import java.util.List;
import java.util.UUID;

public interface RoomTypeService {
    ResponseRoomType createRoomType(RequestRoomType dto);
    List<ResponseRoomType> getAllRoomTypes();
    ResponseRoomType getRoomTypeById(UUID id);
    ResponseRoomType updateRoomType(UUID id, RequestRoomType dto);
    void deleteRoomType(UUID id);
}
