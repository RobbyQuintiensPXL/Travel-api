package be.pxl.travelapi.models;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    private String cityName;

    @ManyToOne
    private Region region;

    @Lob
    private byte[] image;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "top_destination")
    boolean isTopDestination;

    public City() {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isTopDestination() {
        return isTopDestination;
    }

    public void setTopDestination(boolean topDestination) {
        isTopDestination = topDestination;
    }
}
