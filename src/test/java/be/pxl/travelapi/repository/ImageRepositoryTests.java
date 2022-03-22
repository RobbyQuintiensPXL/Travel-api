package be.pxl.travelapi.repository;

import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Image;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@DataJpaTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ImageRepositoryTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ImageRepository imageRepository;

    private Image image;

    public void persist(){
        image = new Image();
        image.setName("testImage.jpg");
//        image.setContent(any(byte[].class));
        entityManager.persist(image);
        entityManager.flush();
    }

    @Test
    public void showAllImages(){
        persist();
        List<Image> imageList = imageRepository.findAll();

        assertThat(imageList).isNotEmpty();
    }

    @Test
    public void showImageByName(){
        persist();
        Image image = imageRepository.findByName("testImage.jpg").get();

        assertThat(image).isNotNull();
    }

}
