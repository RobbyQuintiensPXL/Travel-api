package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CreateRegionResource;
import be.pxl.travelapi.dto.RegionDto;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CountryRepository;
import be.pxl.travelapi.repository.RegionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RegionServiceTests {

    @MockBean
    private RegionRepository regionRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private RegionService regionService;


    @Test
    public void getAllRegions(){
        List<Region> regionList = new LinkedList<>();
        regionList.add(new Region());

        when(regionRepository.findAll()).thenReturn(regionList);

        List<RegionDto> regionDtoList = regionService.listAllRegions();
        assertEquals(1, regionDtoList.size());
    }

    @Test
    public void getRegionByName(){
        Region region = new Region();
        region.setRegionName("TestRegion");
        when(regionRepository.findRegionByRegionName(any())).thenReturn(java.util.Optional.of(region));

        RegionDto regionDto = regionService.getRegionByName(region.getRegionName());
        assertEquals(regionDto.getRegionName(), region.getRegionName());
    }

    @Test
    public void getRegionsByCountry(){
        List<Region> regionList = new LinkedList<>();
        regionList.add(new Region());
        when(regionRepository.findRegionsByCountry_CountryCode(any())).thenReturn(regionList);

        List<RegionDto> regionDtoList = regionService.listRegionsByCountry("NL");
        assertEquals(1, regionDtoList.size());
    }

    @Test
    public void addRegion(){
        Country newCountry = new Country();
        newCountry.setCountryCode("NL");
        Region newRegion = new Region();
        newRegion.setCountry(newCountry);
        when(countryRepository.findCountryByCountryCode(anyString())).thenReturn(java.util.Optional.of(newCountry));
        when(regionRepository.save(any(Region.class))).thenReturn(newRegion);
        CreateRegionResource regionResource = new CreateRegionResource(newRegion.getRegionName(), newCountry.getCountryCode());

        regionService.addRegion(regionResource);
    }
}