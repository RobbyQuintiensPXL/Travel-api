package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CountryDto {

    private final String countryName;
    private final String countryCode;
    @JsonManagedReference
    private final List<RegionDto> regionList;

    public CountryDto(Country country) {
        this.countryName = country.getCountryName();
        this.countryCode = country.getCountryCode();
        this.regionList = country.getRegionList().stream().map(RegionDto::new).collect(Collectors.toList());
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public List<RegionDto> getRegionList() {
        return regionList;
    }
}
