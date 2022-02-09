package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.models.Room;

import java.util.List;
import java.util.Set;

public class HotelDto {

    private final String hotelName;
    private final int stars;
    private final City city;
    private final String address;
    private final byte[] imageHotel;
    private final byte[] imageRoomOne;
    private final byte[] imageRoomTwo;
    private final boolean isTopHotel;

    public HotelDto(Hotel hotel){
        this.hotelName = hotel.getHotelName();
        this.stars = hotel.getStars();
        this.city = hotel.getCity();
        this.address = hotel.getAddress();
        this.imageHotel = hotel.getImageHotel();
        this.imageRoomOne = hotel.getImageRoomOne();
        this.imageRoomTwo = hotel.getImageRoomTwo();
        this.isTopHotel = hotel.isTopHotel();
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getStars() {
        return stars;
    }

    public City getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public byte[] getImageHotel() {
        return imageHotel;
    }

    public byte[] getImageRoomOne() {
        return imageRoomOne;
    }

    public byte[] getImageRoomTwo() {
        return imageRoomTwo;
    }

    public boolean isTopHotel() {
        return isTopHotel;
    }
}
