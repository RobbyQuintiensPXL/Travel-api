package be.pxl.travelapi.models;


import javax.persistence.*;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    private Region region;

//    @Lob
//    private byte[] image;

    @ManyToOne
    private Image image;

    @Column(name = "top_destination")
    boolean isTopDestination;

    public City() {
        //Empty constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

//    public byte[] getImage() {
//        return image;
//    }
//
//    public void setImage(byte[] image) {
//        this.image = image;
//    }


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isTopDestination() {
        return isTopDestination;
    }

    public void setTopDestination(boolean topDestination) {
        isTopDestination = topDestination;
    }

}
