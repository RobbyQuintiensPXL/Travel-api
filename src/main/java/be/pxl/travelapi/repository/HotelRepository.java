package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findHotelByHotelName(String hotelName);

    Optional<Hotel> findHotelByHotelNameAndCity_CityName(String hotelName, String cityName);

    List<Hotel> findHotelsByStars(int stars);

    List<Hotel> findHotelsByCity_CityName(String cityName);

    List<Hotel> findHotelsByCity_CityNameAndStars(String cityName, int stars);

}
