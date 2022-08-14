package be.pxl.travelapi.services;


import be.pxl.travelapi.dto.CreateHotelResource;
import be.pxl.travelapi.dto.HotelDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.models.Room;
import be.pxl.travelapi.repository.CityRepository;
import be.pxl.travelapi.repository.HotelRepository;
import be.pxl.travelapi.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class HotelServiceTests {

    @MockBean
    private HotelRepository hotelRepository;

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private HotelService hotelService;

    @Test
    public void getAllHotels(){
        List<Hotel> hotelList = new LinkedList<>();
        hotelList.add(new Hotel());

        when(hotelRepository.findAll()).thenReturn(hotelList);

        assertEquals(1, hotelList.size());
    }

    @Test
    public void getHotelsByCity(){
        Image image = new Image();
        image.setName("test.jpg");
        Hotel hotel = new Hotel();
        hotel.setImageHotel(image);
        List<Hotel> hotelList = new LinkedList<>();
        hotelList.add(hotel);
        when(hotelRepository.findHotelsByCity_CityName(any())).thenReturn(hotelList);

        List<HotelDto> hotelDtoList = hotelService.getHotelsByCity(any());
        assertEquals(1, hotelDtoList.size());
    }

    @Test
    public void getHotelsByStars(){
        Image image = new Image();
        image.setName("test.jpg");
        Hotel hotel = new Hotel();
        hotel.setImageHotel(image);
        List<Hotel> hotelList = new LinkedList<>();
        hotelList.add(hotel);
        when(hotelRepository.findHotelsByStars(any(int.class))).thenReturn(hotelList);

        List<HotelDto> hotelDtoList = hotelService.getHotelsByStars(any(int.class));
        assertEquals(1, hotelDtoList.size());
    }

    @Test
    public void getHotelByName(){
        Image image = new Image();
        image.setName("test.jpg");
        Hotel hotel = new Hotel();
        hotel.setHotelName("Testhotel");
        hotel.setImageHotel(image);
        when(hotelRepository.findHotelByHotelName(any(String.class))).thenReturn(java.util.Optional.of(hotel));
        HotelDto hotelDto = hotelService.getHotelByName(hotel.getHotelName());
        assertEquals(hotelDto.getHotelName(), hotel.getHotelName());
    }

    @Test
    public void getHotelsByCityAndStars(){
        Image image = new Image();
        image.setName("test.jpg");
        List<Hotel> hotelList = new LinkedList<>();
        Hotel hotel = new Hotel();
        hotel.setHotelName("TestHotel");
        hotel.setStars(5);
        hotel.setImageHotel(image);
        hotelList.add(hotel);
        when(hotelRepository.findHotelsByCity_CityNameAndStars(hotel.getHotelName(), hotel.getStars())).thenReturn(hotelList);

        List<HotelDto> hotelDtoList = hotelService.getHotelByCityAndStars(hotel.getHotelName(), hotel.getStars());
        assertTrue(hotelDtoList.size() >= 1);
    }

//    @Test
//    public void addHotel() throws IOException {
//        Hotel hotel = new Hotel();
//        City city = new City();
//        city.setCityName("TestCity");
//        Room room = new Room();
//        room.setHotel(hotel);
//        List<Room> roomList = new ArrayList<>();
//        roomList.add(room);
//        when(roomRepository.save(any(Room.class))).thenReturn(room);
//        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);
//        when(cityRepository.findCityByCityName(any(String.class))).thenReturn(java.util.Optional.of(city));
//        String hotelName = "hotelname.jpg";
//        String roomOne = "roomOne.jpg";
//        String roomTwo = "roomTwo.jpg";
//
//        MockMultipartFile fileHotel = new MockMultipartFile("user-file",hotelName,
//                "text/plain", "test data".getBytes());
//        MockMultipartFile fileRoomOne = new MockMultipartFile("user-file",roomOne,
//                "text/plain", "test data".getBytes());
//        MockMultipartFile fileRoomTwo = new MockMultipartFile("user-file",roomTwo,
//                "text/plain", "test data".getBytes());
//
//        CreateHotelResource hotelResource = new CreateHotelResource(any(String.class), any(int.class), city.getCityName(), null, hotel.getRooms(), fileHotel, fileRoomOne, fileRoomTwo, true);
//        hotelService.addHotel(hotelResource);
//    }

    @Test
    public void throwExceptionHotelNameNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> hotelService.getHotelByName("test"));
        assertEquals("Hotel [test] not found", thrown.getMessage());
    }

    @Test
    public void throwExceptionHotelStarsNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> hotelService.getHotelsByStars(7));
        assertEquals("No hotels found with 7 stars", thrown.getMessage());
    }

    @Test
    public void throwExceptionHotelByCityNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> hotelService.getHotelsByCity("test"));
        assertEquals("City [test] not found", thrown.getMessage());
    }

    @Test
    public void throwExceptionHotelByCityAndStarsNotFound() {
        Throwable thrown = assertThrows(BusinessException.class, () -> hotelService.getAllHotels());
        assertEquals("No hotels found", thrown.getMessage());
    }

}
