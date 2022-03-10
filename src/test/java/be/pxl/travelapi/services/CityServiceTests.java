package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CityDto;
import be.pxl.travelapi.dto.CreateCityResource;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CityRepository;
import be.pxl.travelapi.repository.RegionRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CityServiceTests {

    @MockBean
    private CityRepository cityRepository;

    @MockBean
    private RegionRepository regionRepository;

    @Autowired
    private CityService cityService;


    @Test
    public void getAllCities(){
        List<City> cityList = new LinkedList<>();
        cityList.add(new City());

        when(cityRepository.findAll()).thenReturn(cityList);

        assertEquals(1, cityList.size());
    }

    @Test
    public void getCityByName(){
        City city = new City();
        city.setCityName("TestCity");
        city.setHotelList(new ArrayList<Hotel>());

        when(cityRepository.findCityByCityName(any())).thenReturn(java.util.Optional.of(city));

        CityDto cityDto = cityService.getCityByName(city.getCityName());

        assertEquals(cityDto.getCityName(), city.getCityName());
    }

    @Test
    public void getCityById(){
        City city = new City();
        city.setHotelList(new ArrayList<Hotel>());

        when(cityRepository.findCityById(any())).thenReturn(java.util.Optional.of(city));

        CityDto cityDto = cityService.getCityById(city.getId());

        assertSame(cityDto.getId(), city.getId());
    }

    @Test
    public void getCitiesByRegion(){
        List<City> cityList = new LinkedList<>();
        City city = new City();
        city.setHotelList(new ArrayList<Hotel>());
        cityList.add(city);
        when(cityRepository.findCitiesByRegion_RegionName(any())).thenReturn(cityList);

        List<CityDto> cityDtoList = cityService.getAllCitiesByRegion("dd");
        assertEquals(1, cityDtoList.size());
    }

    @Test
    public void addCity() throws IOException {
        City newCity = new City();
        Region region = new Region();
        region.setRegionName("Limburg");
        when(cityRepository.save(any(City.class))).thenReturn(newCity);
        when(regionRepository.findRegionByRegionName(anyString())).thenReturn(java.util.Optional.of(region));
        String fileName = "test.jpg";
        MockMultipartFile file = new MockMultipartFile("user-file",fileName,
                "text/plain", "test data".getBytes());
        CreateCityResource cityResource = new CreateCityResource(newCity.getCityName(), region.getRegionName(), file , false);
        cityService.addCity(cityResource);
    }

    @Test
    public void throwExceptionCityNameNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> cityService.getCityByName("test"));
        assertEquals("City [test] not found", thrown.getMessage());
    }

    @Test
    public void throwExceptionRegionNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> cityService.getAllCitiesByRegion("test"));
        assertEquals("Region [test] not found", thrown.getMessage());
    }

    @Test
    public void throwExceptionCityIdNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> cityService.getCityById(100L));
        assertEquals("City with id [100] not found", thrown.getMessage());
    }

    @Test
    public void throwExceptionCitiesNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> cityService.getAllCities());
        assertEquals("No cities found", thrown.getMessage());
    }
}
