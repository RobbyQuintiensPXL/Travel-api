package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Region;
import com.sun.istack.NotNull;

import java.util.Set;

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
