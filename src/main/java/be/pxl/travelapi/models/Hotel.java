package be.pxl.travelapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
