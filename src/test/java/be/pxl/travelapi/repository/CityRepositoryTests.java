package be.pxl.travelapi.repository;


import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Country;
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
public class CityRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    private City city;

    public void persist(){
        city = new City();
        city.setCityName("TestCity");
        entityManager.persist(city);
        entityManager.flush();
    }

    @Test
    public void showAllCities(){
        persist();
        List<City> cities = cityRepository.findAll();

        assertThat(cities).isNotEmpty();
        assertThat(cities.get(0).getCityName()).isEqualTo(city.getCityName());
    }

    @Test
    public void showCityByName(){
        persist();
        Optional<City> foundCity = cityRepository.findCityByCityName(city.getCityName());

        assertThat(foundCity).isNotNull();
        assertThat(foundCity.get().getCityName()).isEqualTo(city.getCityName());
    }

    @Test
    public void showCityById(){
        persist();
        Optional<City> foundCity = cityRepository.findCityById(city.getId());

        assertThat(foundCity).isNotNull();
        assertThat(foundCity.get().getCityName()).isEqualTo(city.getCityName());
        assertThat(foundCity.get().getId()).isEqualTo(city.getId());
    }
}