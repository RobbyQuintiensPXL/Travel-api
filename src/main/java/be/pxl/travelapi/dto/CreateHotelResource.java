package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Room;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CreateHotelResource {

    @NotNull
    private final String hotelName;
    @NotNull
    private final int stars;
    @NotNull
    private final String city;
    @NotNull
    private final String address;
    private final List<Room> rooms;
    private final String imageHotel;
    private final MultipartFile imageRoomOne;
    private final MultipartFile imageRoomTwo;
    private final boolean isTopHotel;
    private final double price;

    public CreateHotelResource(@NotNull String hotelName, @NotNull int stars, @NotNull String city,
                               @NotNull String address, List<Room> rooms, String imageHotel,
                               MultipartFile imageRoomOne, MultipartFile imageRoomTwo, boolean isTopHotel, double price) {
        this.hotelName = hotelName;
        this.stars = stars;
        this.city = city;
        this.address = address;
        this.rooms = rooms;
        this.imageHotel = imageHotel;
        this.imageRoomOne = imageRoomOne;
        this.imageRoomTwo = imageRoomTwo;
        this.isTopHotel = isTopHotel;
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getStars() {
        return stars;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public String getImageHotel() {
        return imageHotel;
    }

    public MultipartFile getImageRoomOne() {
        return imageRoomOne;
    }

    public MultipartFile getImageRoomTwo() {
        return imageRoomTwo;
    }

    public boolean isTopHotel() {
        return isTopHotel;
    }

    public double getPrice() {
        return price;
    }
}
