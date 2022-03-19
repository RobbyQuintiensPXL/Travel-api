package be.pxl.travelapi.dto;

public class CreateImageResource {

    private final byte[] content;
    private final String name;

    public CreateImageResource(byte[] content, String name) {
        this.content = content;
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
