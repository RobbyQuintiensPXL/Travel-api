package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Image;

public class ImageDto {

    private final Long id;
    private final String name;
    private final byte[] content;

    public ImageDto(Image image){
        this.id = image.getId();
        this.name = image.getName();
        this.content = image.getContent();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getContent() {
        return content;
    }
}
