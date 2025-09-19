package com.luxury_hotel.room_service.controller;


import com.luxury_hotel.room_service.dtos.RequestRoomType;
import com.luxury_hotel.room_service.dtos.ResponseRoomType;
import com.luxury_hotel.room_service.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<ResponseRoomType> createRoomType(@RequestBody RequestRoomType dto) {
        return ResponseEntity.ok(roomTypeService.createRoomType(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseRoomType>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeService.getAllRoomTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRoomType> getRoomTypeById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomTypeService.getRoomTypeById(id));
    }

    @PutMapping("/{id}") // ✅ FIXED
    public ResponseEntity<ResponseRoomType> updateRoomType(
            @PathVariable UUID id,
            @RequestBody RequestRoomType dto
    ) {
        return ResponseEntity.ok(roomTypeService.updateRoomType(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoomType(@PathVariable UUID id) {
        roomTypeService.deleteRoomType(id);
        return ResponseEntity.noContent().build();
    }
}

