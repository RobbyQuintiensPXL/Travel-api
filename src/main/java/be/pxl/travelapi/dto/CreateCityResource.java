package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Image;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotNull;
public class CreateCityResource {

    @NotNull
    private final String cityName;

    @NotNull
    private final String region;
    private final Image image;
    private final boolean topDestination;

    public CreateCityResource(@NotNull String cityName, @NotNull String region,
                              Image image, boolean topDestination){
        this.cityName = cityName;
        this.region = region;
        this.topDestination = topDestination;
        this.image = image;
    }

    public String getCityName() {
        return cityName;
    }

    public String getRegion() {
        return region;
    }

    public boolean isTopDestination() {
        return topDestination;
    }

    public Image getImage() {
        return image;
    }
}
