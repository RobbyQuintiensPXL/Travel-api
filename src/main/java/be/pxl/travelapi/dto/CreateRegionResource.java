package be.pxl.travelapi.dto;

import com.sun.istack.NotNull;

public class CreateRegionResource {

    @NotNull
    private final String regionName;
    @NotNull
    private final String countryCode;
    private final String imageUrl;

    public CreateRegionResource(@NotNull String regionName, @NotNull String countryCode, String imageUrl) {
        this.regionName = regionName;
        this.countryCode = countryCode;
        this.imageUrl = imageUrl;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
