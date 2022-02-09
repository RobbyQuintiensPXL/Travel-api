package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    Optional<Country> findCountryByCountryName(String countryName);

    Optional<Country> findCountryByCountryCode(String countryCode);
}
