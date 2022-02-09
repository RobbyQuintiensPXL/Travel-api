package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Optional<Region> findRegionByRegionName(String regionName);


    List<Region> findRegionsByCountry_CountryCode(String countryCode);
}
