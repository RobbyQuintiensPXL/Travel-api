package be.pxl.travelapi.dto;

import javax.validation.constraints.NotNull;

public class CreateRoomResource {

    @NotNull
    private final String roomNumber;
    @NotNull
    private final String roomType;
    @NotNull
    private final int beds;
    @NotNull
    private final double pricePerNight;

    public CreateRoomResource(@NotNull String roomNumber, @NotNull String roomType,
                              @NotNull int beds, @NotNull double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}
