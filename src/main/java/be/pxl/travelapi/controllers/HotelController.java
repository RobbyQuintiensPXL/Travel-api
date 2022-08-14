package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CreateHotelResource;
import be.pxl.travelapi.dto.CreateRoomResource;
import be.pxl.travelapi.dto.HotelDto;
import be.pxl.travelapi.dto.RoomDto;
import be.pxl.travelapi.services.HotelService;
import be.pxl.travelapi.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "http://localhost:58272")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels(){
        List<HotelDto> hotelDtoList = hotelService.getAllHotels();
        return new ResponseEntity<>(hotelDtoList, HttpStatus.OK);
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<List<HotelDto>> getHotelsByCity(@PathVariable("cityName") String cityName){
        List<HotelDto> hotelDtoList = hotelService.getHotelsByCity(cityName);
        return new ResponseEntity<>(hotelDtoList, HttpStatus.OK);
    }

    @GetMapping("/stars/{stars}")
    public ResponseEntity<List<HotelDto>> getHotelsByStars(@PathVariable("stars") int stars){
        List<HotelDto> hotelDtoList = hotelService.getHotelsByStars(stars);
        return new ResponseEntity<>(hotelDtoList, HttpStatus.OK);
    }

    @GetMapping("/{hotelName}")
    public ResponseEntity<HotelDto> getHotelByName(@PathVariable("hotelName") String hotelName){
        HotelDto hotelDto = hotelService.getHotelByName(hotelName);
        return new ResponseEntity<>(hotelDto, HttpStatus.OK);
    }

    @GetMapping("/{cityName}/{stars}")
    public ResponseEntity<List<HotelDto>> getHotelsByCityAndStars(@PathVariable("cityName") String cityName,
                                                                  @PathVariable("stars") int stars){
        List<HotelDto> hotelDtoList = hotelService.getHotelByCityAndStars(cityName, stars);
        return new ResponseEntity<>(hotelDtoList, HttpStatus.OK);
    }

//    @PostMapping(consumes = { "multipart/form-data" })
//    public ResponseEntity<Void> addHotel(@ModelAttribute @Valid CreateHotelResource hotelResource) throws IOException {
//        hotelService.addHotel(hotelResource);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/{hotelid}/rooms")
    public ResponseEntity<List<RoomDto>> getRoomsByHotel(@PathVariable("hotelid") Long id){
        List<RoomDto> roomDtoList = roomService.getRoomsByHotelId(id);
        return new ResponseEntity<>(roomDtoList, HttpStatus.OK);
    }

    @PostMapping("/{hotelid}/addroom")
    public ResponseEntity<Void> addRoomToHotel(@PathVariable("hotelid") Long hotelId,
                                               @RequestBody @Valid CreateRoomResource roomResource){
        roomService.addRoomToHotel(hotelId, roomResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
