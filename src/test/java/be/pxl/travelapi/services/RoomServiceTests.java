package be.pxl.travelapi.services;


import be.pxl.travelapi.dto.CreateRoomResource;
import be.pxl.travelapi.dto.RegionDto;
import be.pxl.travelapi.dto.RoomDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.models.Room;
import be.pxl.travelapi.models.RoomType;
import be.pxl.travelapi.repository.CountryRepository;
import be.pxl.travelapi.repository.HotelRepository;
import be.pxl.travelapi.repository.RegionRepository;
import be.pxl.travelapi.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RoomServiceTests {

    @MockBean
    private RoomRepository roomRepository;

    @MockBean
    private HotelRepository hotelRepository;

    @Autowired
    private RoomService roomService;


    @Test
    public void getAllRoomsTest(){
        List<Room> roomList = new LinkedList<>();
        roomList.add(new Room());

        when(roomRepository.findAll()).thenReturn(roomList);

        List<RoomDto> roomDtoList = roomService.getAllRooms();
        assertEquals(1, roomDtoList.size());
    }

    @Test
    public void getRoomsByHotelIdTest(){
        List<Room> roomList = new LinkedList<>();
        roomList.add(new Room());

        when(roomRepository.findRoomsByHotel_Id(any(Long.class))).thenReturn(roomList);

        List<RoomDto> roomDtoList = roomService.getRoomsByHotelId(1L);
        assertEquals(1, roomDtoList.size());
    }

    @Test
    public void throwExceptionRoomByHotelIdNotFound() {
        Throwable thrown = assertThrows(BusinessException.class, () -> roomService.getRoomsByHotelId(100L));
        assertEquals("Hotel with id [100] not found", thrown.getMessage());
    }

    @Test
    public void throwExceptionRoomByHotelNameNotFound() {
        Throwable thrown = assertThrows(BusinessException.class, () -> roomService.getRoomsByHotel("test"));
        assertEquals("Hotel [test] not found", thrown.getMessage());
    }


//    @Test
//    public void addRoomToHotelTest(){
//        Hotel hotel = new Hotel();
//        hotel.setId(1L);
//        Room room = new Room();
//        room.setRoomNumber("1A");
//        room.setRoomType(RoomType.BASIC);
//        room.setBeds(2);
//        room.setPricePerNight(100);
//        room.setHotel(hotel);
//
//        when(roomRepository.save(any(Room.class))).thenReturn(room);
//        when(hotelRepository.getById(hotel.getId())).thenReturn(hotel);
//
//        CreateRoomResource roomResource = new CreateRoomResource(room.getRoomNumber(), room.getRoomType().toString(),
//                room.getBeds(), room.getPricePerNight());
//
//        roomService.addRoomToHotel(hotel.getId(), roomResource);
//    }
}
