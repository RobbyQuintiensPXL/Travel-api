package be.pxl.travelapi.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name")
    private String hotelName;

    private int stars;

    @ManyToOne
    private City city;

    private String address;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;

    @ManyToOne
    private Image imageHotel;

    @Lob
    @Column(name = "image_room_one")
    private byte[] imageRoomOne;

    @Lob
    @Column(name = "image_room_two")
    private byte[] imageRoomTwo;

    @Column(name = "is_top_hotel")
    private boolean isTopHotel;

    public Hotel() {
        //Empty constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRoomSet(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Image getImageHotel() {
        return imageHotel;
    }

    public void setImageHotel(Image imageHotel) {
        this.imageHotel = imageHotel;
    }

    public byte[] getImageRoomOne() {
        return imageRoomOne;
    }

    public void setImageRoomOne(byte[] imageRoomOne) {
        this.imageRoomOne = imageRoomOne;
    }

    public byte[] getImageRoomTwo() {
        return imageRoomTwo;
    }

    public void setImageRoomTwo(byte[] imageRoomTwo) {
        this.imageRoomTwo = imageRoomTwo;
    }

    public boolean isTopHotel() {
        return isTopHotel;
    }

    public void setTopHotel(boolean topHotel) {
        isTopHotel = topHotel;
    }
}
