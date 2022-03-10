package be.pxl.travelapi.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Country {

    @Id
    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;


    public Country() {
        //Empty constructor
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
