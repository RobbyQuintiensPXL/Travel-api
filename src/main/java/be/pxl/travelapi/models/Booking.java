package be.pxl.travelapi.models;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Hotel hotel;

    @OneToOne
    private Room room;

    @Column(name = "from_date")
    private LocalDateTime fromDate;

    @Column(name = "till_date")
    private LocalDateTime tillDate;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getTillDate() {
        return tillDate;
    }

    public void setTillDate(LocalDateTime tillDate) {
        this.tillDate = tillDate;
    }
}
