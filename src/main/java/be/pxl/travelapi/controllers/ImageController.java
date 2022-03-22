package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.ImageDto;
import be.pxl.travelapi.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:53403")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<List<ImageDto>> getImages(){
        List<ImageDto> imageDtoList = imageService.getImages();
        return new ResponseEntity<>(imageDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{imageName}")
    public ResponseEntity<ImageDto> getImageByName(@PathVariable("imageName") String imageName){
        ImageDto image = imageService.getImage(imageName);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
