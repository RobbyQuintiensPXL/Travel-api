package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CityDto;
import be.pxl.travelapi.dto.CreateCityResource;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CityRepository;
import be.pxl.travelapi.repository.RegionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
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

        assertTrue(cityList.size() == 1);
    }

    @Test
    public void getCityByName(){
        City city = new City();
        city.setCityName("TestCity");

        when(cityRepository.findCityByCityName(any())).thenReturn(java.util.Optional.of(city));

        CityDto cityDto = cityService.getCityByName(city.getCityName());

        assertTrue(cityDto.getCityName().equals(city.getCityName()));
    }

    @Test
    public void getCityById(){
        City city = new City();

        when(cityRepository.findCityById(any())).thenReturn(java.util.Optional.of(city));

        CityDto cityDto = cityService.getCityById(city.getId());

        assertTrue(cityDto.getId() == city.getId());
    }

    @Test
    public void getCitiesByRegion(){
        List<City> cityList = new LinkedList<>();
        cityList.add(new City());
        when(cityRepository.findCitiesByRegion_RegionName(any())).thenReturn(cityList);

        List<CityDto> cityDtoList = cityService.getAllCitiesByRegion("dd");
        assertTrue(cityDtoList.size() == 1);
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
}
