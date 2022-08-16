package be.pxl.travelapi.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
