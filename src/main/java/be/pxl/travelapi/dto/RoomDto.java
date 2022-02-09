package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Reservation;
import be.pxl.travelapi.models.Room;
import be.pxl.travelapi.models.RoomType;

import java.util.Set;

public class RoomDto {

    private final Long id;
    private final String roomNumber;
    private final RoomType roomType;
    private final int beds;
    private final double pricePerNight;
    private final Set<Reservation> reservations;

    public RoomDto(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.roomType = room.getRoomType();
        this.beds = room.getBeds();
        this.pricePerNight = room.getPricePerNight();
        this.reservations = room.getReservations();
    }

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getBeds() {
        return beds;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }
}
