package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CreateRoomResource;
import be.pxl.travelapi.dto.RoomDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Room;
import be.pxl.travelapi.models.RoomType;
import be.pxl.travelapi.repository.HotelRepository;
import be.pxl.travelapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public List<RoomDto> getAllRooms(){
        return roomRepository.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    public List<RoomDto> getRoomsByHotel(String hotelName){
        return roomRepository.findRoomsByHotel_HotelName(hotelName).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    public List<RoomDto> getRoomsByHotelId(Long id){
        return roomRepository.findRoomsByHotel_Id(id).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    public void addRoomToHotel(Long id, CreateRoomResource roomResource){
        Optional<Hotel> foundHotel = hotelRepository.findById(id);
        if(foundHotel.isEmpty()){
            throw new BusinessException("Hotel with ID [" + id + "] not found.");
        }

        Room room = new Room();
        room.setHotel(foundHotel.get());
        room.setBeds(roomResource.getBeds());
        room.setPricePerNight(roomResource.getPricePerNight());
        room.setRoomNumber(roomResource.getRoomNumber());
        room.setRoomType(RoomType.valueOf(roomResource.getRoomType()));

        roomRepository.save(room);
    }
}
