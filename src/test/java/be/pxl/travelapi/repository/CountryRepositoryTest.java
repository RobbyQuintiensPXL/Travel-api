package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CountryRepository countryRepository;

    private Country country;

    public void persist(){
        country = new Country();
        country.setCountryName("Belgium");
        country.setCountryCode("BE");
        entityManager.persist(country);
        entityManager.flush();
    }

    @Test
    public void showAllCountries(){
        persist();
        List<Country> countries = countryRepository.findAll();

        assertThat(countries).isNotEmpty();
        assertThat(countries.get(0).getCountryName()).isEqualTo(country.getCountryName());
        assertThat(countries.get(0).getCountryCode()).isEqualTo(country.getCountryCode());
    }

    @Test
    public void showCountryByName(){
        persist();
        Optional<Country> foundCountry = countryRepository.findCountryByCountryName(country.getCountryName());

        assertThat(foundCountry).isNotNull();
        assertThat(foundCountry.get().getCountryName()).isEqualTo(country.getCountryName());
        assertThat(foundCountry.get().getCountryCode()).isEqualTo(country.getCountryCode());
    }

    @Test
    public void showCountryByCode(){
        persist();
        Optional<Country> foundCountry = countryRepository.findCountryByCountryCode(country.getCountryCode());

        assertThat(foundCountry).isNotNull();
        assertThat(foundCountry.get().getCountryName()).isEqualTo(country.getCountryName());
        assertThat(foundCountry.get().getCountryCode()).isEqualTo(country.getCountryCode());
    }
}
