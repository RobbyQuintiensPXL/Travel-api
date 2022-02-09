package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findRoomsByHotel_HotelName(String hotelName);
    List<Room> findRoomsByHotel_Id(Long id);

}
