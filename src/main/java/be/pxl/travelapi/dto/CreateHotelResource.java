package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Room;
import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public class CreateHotelResource {

    @NotNull
    private final String hotelName;
    @NotNull
    private final int stars;
    @NotNull
    private final String city;
    @NotNull
    private final String address;
    private final Set<Room> rooms;
    private final MultipartFile imageHotel;
    private final MultipartFile imageRoomOne;
    private final MultipartFile imageRoomTwo;
    private final boolean isTopHotel;

    public CreateHotelResource(@NotNull String hotelName, @NotNull int stars, @NotNull String city,
                               @NotNull String address, Set<Room> rooms, MultipartFile imageHotel,
                               MultipartFile imageRoomOne, MultipartFile imageRoomTwo, boolean isTopHotel) {
        this.hotelName = hotelName;
        this.stars = stars;
        this.city = city;
        this.address = address;
        this.rooms = rooms;
        this.imageHotel = imageHotel;
        this.imageRoomOne = imageRoomOne;
        this.imageRoomTwo = imageRoomTwo;
        this.isTopHotel = isTopHotel;
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public MultipartFile getImageHotel() {
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
}
