package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HotelRepository hotelRepository;

    private Hotel hotel;

    public void persist(){
        hotel = new Hotel();
        hotel.setHotelName("TestHotel");
        hotel.setAddress("TestAdres");
        hotel.setStars(5);
        entityManager.persist(hotel);
        entityManager.flush();
    }

    @Test
    public void showAllHotels(){
        persist();
        List<Hotel> hotelList = hotelRepository.findAll();

        assertThat(hotelList).isNotEmpty();
        assertThat(hotelList.get(0).getHotelName()).isEqualTo(hotel.getHotelName());
    }

    @Test
    public void showHotelByName(){
        persist();
        Optional<Hotel> foundHotel = hotelRepository.findHotelByHotelName(hotel.getHotelName());

        assertThat(foundHotel).isNotNull();
        assertThat(foundHotel.get().getAddress()).isEqualTo(hotel.getAddress());
    }

    @Test
    public void showHotelsByStars(){
        persist();
        List<Hotel> hotelList = hotelRepository.findHotelsByStars(hotel.getStars());

        assertThat(hotelList).isNotEmpty();
        assertThat(hotelList.get(0).getHotelName()).isEqualTo(hotel.getHotelName());
    }

    @Test
    public void showHotelsByHotelNameAndCityName(){
        persist();
        City city = new City();
        city.setCityName("TestCity");
        hotel.setCity(city);

        entityManager.persist(city);
        entityManager.flush();

        Optional<Hotel> foundHotel = hotelRepository.findHotelByHotelNameAndCity_CityName(hotel.getHotelName(), city.getCityName());

        assertThat(foundHotel).isNotNull();
        assertThat(foundHotel.get().getHotelName()).isEqualTo(hotel.getHotelName());
        assertThat(foundHotel.get().getCity().getCityName()).isEqualTo(city.getCityName());
        assertThat(foundHotel.get().getStars()).isEqualTo(hotel.getStars());
    }

    @Test
    public void showHotelsbyCityName(){
        persist();
        City city = new City();
        city.setCityName("TestCity");
        hotel.setCity(city);

        entityManager.persist(city);
        entityManager.flush();

        List<Hotel> hotelList = hotelRepository.findHotelsByCity_CityName(city.getCityName());

        assertThat(hotelList).isNotNull();
        assertThat(hotelList.get(0).getHotelName()).isEqualTo(hotel.getHotelName());
        assertThat(hotelList.get(0).getCity().getCityName()).isEqualTo(city.getCityName());
    }

    @Test
    public void showHotelsByCityNameAndStars(){
        persist();
        City city = new City();
        city.setCityName("TestCity");
        hotel.setCity(city);

        entityManager.persist(city);
        entityManager.flush();

        List<Hotel> hotelList = hotelRepository.findHotelsByCity_CityNameAndStars(city.getCityName(), hotel.getStars());

        assertThat(hotelList).isNotNull();
        assertThat(hotelList.get(0).getHotelName()).isEqualTo(hotel.getHotelName());
        assertThat(hotelList.get(0).getCity().getCityName()).isEqualTo(city.getCityName());
        assertThat(hotelList.get(0).getStars()).isEqualTo(hotel.getStars());
    }
}
