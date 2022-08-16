package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CreateRegionResource;
import be.pxl.travelapi.dto.RegionDto;
import be.pxl.travelapi.services.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/regions")
@CrossOrigin(origins = "http://localhost:58272")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<RegionDto>> getAllRegions() {
        List<RegionDto> regionList = regionService.listAllRegions();
        return new ResponseEntity<>(regionList, HttpStatus.OK);
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<List<RegionDto>> getRegionsByCountry(@PathVariable("countryCode") String countryCode) {
        List<RegionDto> regionList = regionService.listRegionsByCountry(countryCode);
        return new ResponseEntity<>(regionList, HttpStatus.OK);
    }

    @GetMapping("/{regionName}")
    public ResponseEntity<RegionDto> getRegionByName(@PathVariable("regionName") String regionName) {
        RegionDto regionDto = regionService.getRegionByName(regionName);
        return new ResponseEntity<>(regionDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addRegion(@RequestBody @Valid CreateRegionResource regionResource) {
        regionService.addRegion(regionResource);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
