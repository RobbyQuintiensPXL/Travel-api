package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.ImageDto;
import be.pxl.travelapi.services.FileStorageService;
import be.pxl.travelapi.services.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "http://localhost:58272")
public class ImageController {

    private final ImageService imageService;
    private final FileStorageService storageService;

    public ImageController(ImageService imageService, FileStorageService storageService) {
        this.imageService = imageService;
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getImages(){
        List<ImageDto> imageDtoList = imageService.getImages();
        return new ResponseEntity<>(imageDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/{imageName}")
    public ResponseEntity<Resource> getImageByName(@PathVariable("imageName") String filename){
        Resource image = storageService.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + image.getFilename() + "\"").body(image);
    }

}
