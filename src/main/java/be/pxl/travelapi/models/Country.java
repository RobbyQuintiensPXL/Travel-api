package be.pxl.travelapi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country {

    @Id
    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;
}
