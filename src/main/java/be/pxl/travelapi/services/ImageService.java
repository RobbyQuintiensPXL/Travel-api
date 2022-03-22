package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.ImageDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public List<ImageDto> getImages(){
        List<ImageDto> imageDtoList = imageRepository.findAll().stream().map(ImageDto::new).collect(Collectors.toList());
        if(imageDtoList.isEmpty()){
            throw new BusinessException("Could not find any images");
        }
        return imageDtoList;
    }

    public ImageDto getImage(String imageName) {
        Optional<ImageDto> image = imageRepository.findByName(imageName).map(ImageDto::new);
        if (image.isEmpty()) {
            throw new BusinessException("Could not find image.");
        }
        return image.get();
    }
}
