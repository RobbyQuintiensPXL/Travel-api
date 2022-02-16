package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.RoomDto;
import be.pxl.travelapi.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:53403")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        List<RoomDto> roomDtos = roomService.getAllRooms();
        return new ResponseEntity<>(roomDtos, HttpStatus.OK);
    }

}
