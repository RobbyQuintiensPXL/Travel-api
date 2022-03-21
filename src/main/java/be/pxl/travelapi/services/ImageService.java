package be.pxl.travelapi.services;

import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image getImage(String imageName) {
        Optional<Image> image = imageRepository.findByName(imageName);
        if (image.isEmpty()) {
            throw new BusinessException("Could not find image.");
        }
        return image.get();
    }
}
