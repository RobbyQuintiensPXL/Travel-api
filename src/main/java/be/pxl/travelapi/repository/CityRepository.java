package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findCityByCityName(String cityName);

    Optional<City> findCityById(Long id);

    List<City> findCitiesByRegion_RegionName(String regionName);
}
