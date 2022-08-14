package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CityDto;
import be.pxl.travelapi.dto.CreateCityResource;
import be.pxl.travelapi.services.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "http://localhost:58272")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @ApiOperation(value = "cities")
    public ResponseEntity<List<CityDto>> getAllCities(){
        List<CityDto> cityDtoList = cityService.getAllCities();
        return new ResponseEntity<>(cityDtoList, HttpStatus.OK);
    }

    @GetMapping("/region/{regionName}")
    public ResponseEntity<List<CityDto>> getCitiesByRegion(@PathVariable("regionName") String regionName){
        List<CityDto> cityDtoList = cityService.getAllCitiesByRegion(regionName);
        return new ResponseEntity<>(cityDtoList, HttpStatus.OK);
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<CityDto> getCityByCityName(@PathVariable("cityName") String cityName){
        CityDto city = cityService.getCityByName(cityName);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/id/{cityId}")
    public ResponseEntity<CityDto> getCityByCityId(@PathVariable("cityId") Long cityId){
        CityDto city = cityService.getCityById(cityId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

//    @PostMapping( consumes = {"*/*"})
//    public ResponseEntity<Void> addCity(@ModelAttribute @Valid CreateCityResource cityResource, @RequestPart MultipartFile image) throws IOException {
//        cityService.addCity(cityResource, image);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
