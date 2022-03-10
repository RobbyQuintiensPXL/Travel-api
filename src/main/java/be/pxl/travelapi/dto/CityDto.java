package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Region;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collectors;

public class CityDto {

    private final Long id;
    private final String cityName;
    private final Region region;
    private final byte[] image;
    private final boolean topDestination;
    private final String regionName;

    public CityDto(City city) {
        this.id = city.getId();
        this.cityName = city.getCityName();
        this.region = city.getRegion();
        this.image = city.getImage();
        this.topDestination = city.isTopDestination();
        this.regionName = city.getRegionName();
    }

    public Long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public Region getRegion() {
        return region;
    }

    public byte[] getImage() {
        return image;
    }

    public boolean isTopDestination() {
        return topDestination;
    }

    public String getRegionName() {
        return regionName;
    }
}
