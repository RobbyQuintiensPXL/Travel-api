package be.pxl.travelapi.dto;

public class CreateImageResource {

    private final String name;

    public CreateImageResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
