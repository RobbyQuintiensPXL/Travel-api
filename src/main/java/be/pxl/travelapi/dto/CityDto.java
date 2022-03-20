package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.models.Region;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collectors;

public class CityDto {

    private final Long id;
    private final String cityName;
    private final Region region;
    private final String cityImage;
    private final boolean topDestination;

    public CityDto(City city) {
        this.id = city.getId();
        this.cityName = city.getCityName();
        this.region = city.getRegion();
        this.topDestination = city.isTopDestination();
        this.cityImage = city.getImage().getName();
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

    public boolean isTopDestination() {
        return topDestination;
    }

    public String getCityImage() {
        return cityImage;
    }
}
