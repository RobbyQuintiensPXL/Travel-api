package be.pxl.travelapi.dto;

import javax.validation.constraints.NotNull;

public class CreateRegionResource {

    @NotNull
    private final String regionName;
    @NotNull
    private final String countryCode;

    public CreateRegionResource(@NotNull String regionName, @NotNull String countryCode) {
        this.regionName = regionName;
        this.countryCode = countryCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
