package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RoomRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoomRepository roomRepository;

    private Room room;

    public void persist(){
        room = new Room();
        room.setRoomNumber("100");
        entityManager.persist(room);
        entityManager.flush();
    }

    @Test
    public void showAllRooms(){
        persist();
        List<Room> roomList = roomRepository.findAll();

        assertThat(roomList).isNotEmpty();
        assertThat(roomList.get(0).getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    public void showAllRoomsByHotel(){
        persist();
        Hotel hotel = new Hotel();
        hotel.setHotelName("TestHotel");
        room.setHotel(hotel);

        entityManager.persist(hotel);
        entityManager.flush();

        List<Room> roomList = roomRepository.findRoomsByHotel_HotelName(hotel.getHotelName());

        assertThat(roomList).isNotEmpty();
        assertThat(roomList.get(0).getHotel().getHotelName()).isEqualTo(hotel.getHotelName());
    }
}
