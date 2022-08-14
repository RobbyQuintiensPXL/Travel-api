package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Image;

public class ImageDto {

    private final Long id;
    private final String name;

    public ImageDto(Image image){
        this.id = image.getId();
        this.name = image.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
