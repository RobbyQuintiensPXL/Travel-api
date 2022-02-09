package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Region;

public class CityDto {

    private final Long id;
    private final String cityName;
    private final Region region;
    private final String imageUrl;
    private final byte[] image;
    private final boolean topDestination;

    public CityDto(City city) {
        this.id = city.getId();
        this.cityName = city.getCityName();
        this.region = city.getRegion();
        this.imageUrl = city.getImageUrl();
        this.image = city.getImage();
        this.topDestination = city.isTopDestination();
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
}
