package be.pxl.travelapi.dto;

import be.pxl.travelapi.models.Reservation;
import be.pxl.travelapi.models.User;

import java.util.Set;

public class UserDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Set<Reservation> reservationList;

    public UserDto(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.reservationList = user.getReservations();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Set<Reservation> getReservationList() {
        return reservationList;
    }
}
