package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;

public class RegionDto {

    private final Long id;
    private final String regionName;
    private final Country country;
    private final String imageUrl;

    public RegionDto(Region region) {
        this.id = region.getId();
        this.regionName = region.getRegionName();
        this.country = region.getCountry();
        this.imageUrl = region.getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }
}
