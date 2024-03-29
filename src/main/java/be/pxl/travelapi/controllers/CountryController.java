package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CountryDto;
import be.pxl.travelapi.dto.CreateCountryResource;
import be.pxl.travelapi.services.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "http://localhost:58272")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        List<CountryDto> countryList = countryService.listAllCountries();
        return new ResponseEntity<>(countryList, HttpStatus.OK);
    }

    @GetMapping("/{countryName}")
    public ResponseEntity<CountryDto> getCountryByName(@PathVariable("countryName") String countryName) {
        CountryDto country = countryService.getCountryByName(countryName);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addCountry(@RequestBody @Valid CreateCountryResource countryResource) {
        countryService.addCountry(countryResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
