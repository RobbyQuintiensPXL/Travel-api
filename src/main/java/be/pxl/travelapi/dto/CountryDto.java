package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Country;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collectors;

public class CountryDto {

    private final String countryName;
    private final String countryCode;

    public CountryDto(Country country) {
        this.countryName = country.getCountryName();
        this.countryCode = country.getCountryCode();
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
