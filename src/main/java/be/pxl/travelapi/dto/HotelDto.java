package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Image;

public class HotelDto {

    private final String hotelName;
    private final int stars;
    private final City city;
    private final String address;
    private final String imageHotel;
    private final byte[] imageRoomOne;
    private final byte[] imageRoomTwo;
    private final boolean isTopHotel;
    private final double price;

    public HotelDto(Hotel hotel){
        this.hotelName = hotel.getHotelName();
        this.stars = hotel.getStars();
        this.city = hotel.getCity();
        this.address = hotel.getAddress();
        this.imageHotel = hotel.getImageHotel().getName();
        this.imageRoomOne = hotel.getImageRoomOne();
        this.imageRoomTwo = hotel.getImageRoomTwo();
        this.isTopHotel = hotel.isTopHotel();
        this.price = hotel.getPrice();
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

    public String getImageHotel() {
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

    public double getPrice() {
        return price;
    }
}
