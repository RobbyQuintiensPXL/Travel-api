package be.pxl.travelapi.dto;

import javax.validation.constraints.NotNull;

public class CreateCountryResource {

    @NotNull
    private final String countryName;

    @NotNull
    private final String countryCode;


    public CreateCountryResource(@NotNull String countryCode, @NotNull String countryName) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
