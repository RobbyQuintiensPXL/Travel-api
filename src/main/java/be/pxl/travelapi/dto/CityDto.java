package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Region;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collectors;

public class CityDto {

    private final Long id;
    private final String cityName;
    @JsonBackReference
    private final Region region;
    private final String regionName;
    private final byte[] image;
    private final boolean topDestination;
    @JsonManagedReference
    private final List<HotelDto> hotelList;

    public CityDto(City city) {
        this.id = city.getId();
        this.cityName = city.getCityName();
        this.region = city.getRegion();
        this.image = city.getImage();
        this.topDestination = city.isTopDestination();
        this.hotelList = city.getHotelList().stream().map(HotelDto::new).collect(Collectors.toList());
        this.regionName = city.getRegion().getRegionName();
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

    public List<HotelDto> getHotelList() {
        return hotelList;
    }

    public String getRegionName() {
        return regionName;
    }
}
