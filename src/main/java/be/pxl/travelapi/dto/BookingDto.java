package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Booking;

import java.time.LocalDateTime;

public class BookingDto {

    private final Long id;
    private final String hotel;
    private final String room;
    private final LocalDateTime fromDate;
    private final LocalDateTime tillDate;

    public BookingDto(Booking booking){
        this.id = booking.getId();
        this.hotel = booking.getHotel().getHotelName();
        this.room = booking.getRoom().getRoomType().name();
        this.fromDate = booking.getFromDate();
        this.tillDate = booking.getTillDate();
    }

    public Long getId() {
        return id;
    }

    public String getHotel() {
        return hotel;
    }

    public String getRoom() {
        return room;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public LocalDateTime getTillDate() {
        return tillDate;
    }
}
