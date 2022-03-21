package be.pxl.travelapi.controllers;

import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:53403")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/{imageName}")
    public ResponseEntity<Image> getImageByName(@PathVariable("imageName") String imageName){
        Image image = imageService.getImage(imageName);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
