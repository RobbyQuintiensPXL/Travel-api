package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Country;

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
