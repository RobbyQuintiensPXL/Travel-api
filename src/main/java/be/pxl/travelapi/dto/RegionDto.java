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
    private final Country country;

    public RegionDto(Region region) {
        this.id = region.getId();
        this.regionName = region.getRegionName();
        this.country = region.getCountry();
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
}
