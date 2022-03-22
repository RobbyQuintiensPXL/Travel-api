package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.ImageDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.repository.ImageRepository;
import be.pxl.travelapi.repository.RoomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ImageServiceTests {

    @MockBean
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @Test
    public void getAllImages(){
        List<Image> imageList = new LinkedList<>();
        imageList.add(new Image());

        when(imageRepository.findAll()).thenReturn(imageList);

        assertEquals(1, imageList.size());
    }

    @Test
    public void getImageByName(){
        Image image = new Image();
        image.setName("testImage.jpg");
        image.setId(1L);
        when(imageRepository.findByName(anyString())).thenReturn(java.util.Optional.of(image));
        ImageDto imageDto = imageService.getImage(image.getName());
        assertEquals(imageDto.getName(), image.getName());
        assertEquals(imageDto.getId(), image.getId());
    }

    @Test
    public void throwExceptionImageNameNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> imageService.getImage("afbeelding"));
        assertEquals("Could not find image.", thrown.getMessage());
    }

    @Test
    public void throwExceptionImagesNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> imageService.getImages());
        assertEquals("Could not find any images", thrown.getMessage());
    }
}
