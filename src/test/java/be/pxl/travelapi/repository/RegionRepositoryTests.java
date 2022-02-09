package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.models.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RegionRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RegionRepository regionRepository;

    private Region region;

    public void persist(){
        region = new Region();
        region.setRegionName("TestRegion");
        entityManager.persist(region);
        entityManager.flush();
    }

    @Test
    public void showAllRegions(){
        persist();
        List<Region> regionList = regionRepository.findAll();

        assertThat(regionList).isNotNull();
        assertThat(regionList.get(0).getRegionName()).isEqualTo(region.getRegionName());
    }

    @Test
    public void showRegionByName(){
        persist();
        Optional<Region> foundRegion = regionRepository.findRegionByRegionName(region.getRegionName());

        assertThat(foundRegion.get()).isNotNull();
    }

    @Test
    public void showRegionsByCountry(){
        persist();
        Country country = new Country();
        country.setCountryCode("BE");
        country.setCountryName("Belgium");
        region.setCountry(country);

        entityManager.persist(country);
        entityManager.flush();

        List<Region> regionList = regionRepository.findRegionsByCountry_CountryCode(country.getCountryCode());

        assertThat(regionList).isNotEmpty();
        assertThat(regionList.get(0).getRegionName()).isEqualTo(region.getRegionName());
        assertThat(regionList.get(0).getCountry().getCountryName()).isEqualTo(region.getCountry().getCountryName());
    }
}
