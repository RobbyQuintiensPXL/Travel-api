package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collectors;

public class RegionDto {

    private final Long id;
    private final String regionName;
    @JsonBackReference
    private final Country country;
    @JsonManagedReference
    private final List<CityDto> cityList;

    public RegionDto(Region region) {
        this.id = region.getId();
        this.regionName = region.getRegionName();
        this.country = region.getCountry();
        this.cityList = region.getCityList().stream().map(CityDto::new).collect(Collectors.toList());
    }

    public String getRegionName() {
        return regionName;
    }

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
    }

    public List<CityDto> getCityList() {
        return cityList;
    }
}
