package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CityDto;
import be.pxl.travelapi.dto.CreateCityResource;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.services.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
@ActiveProfiles("test")
public class CityControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CityService cityService;

    //TODO make global method
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] asBytesString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllCities() throws Exception {
        City city = new City();
        city.setCityName("TestCity");
        city.setTopDestination(true);
        Image image = new Image();
        image.setName("test.jpg");
        city.setImage(image);

        List<CityDto> cityDtoList = Stream.of(city).map(CityDto::new).collect(Collectors.toList());

        given(cityService.getAllCities()).willReturn(cityDtoList);

        mvc.perform(get("/cities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cityName", is(city.getCityName())));
    }

    @Test
    public void getCitiesByRegion() throws Exception {
        City city = new City();
        Region region = new Region();
        region.setRegionName("TestRegion");
        city.setCityName("TestCity");
        city.setRegion(region);
        Image image = new Image();
        image.setName("test.jpg");
        city.setImage(image);

        List<CityDto> cityDtoList = Stream.of(city).map(CityDto::new).collect(Collectors.toList());

        given(cityService.getAllCitiesByRegion(region.getRegionName())).willReturn(cityDtoList);

        mvc.perform(get("/cities/region/TestRegion")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cityName", is(city.getCityName())));
    }

    @Test
    public void getCityByCityName() throws Exception {
        City city = new City();
        city.setId(1L);
        city.setCityName("TestCity");
        Image image = new Image();
        image.setName("test.jpg");
        city.setImage(image);
        CityDto cityDto = new CityDto(city);

        given(cityService.getCityByName(city.getCityName())).willReturn(cityDto);

        mvc.perform(get("/cities/TestCity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cityName", is(city.getCityName())));
    }

    @Test
    public void getCityByCityId() throws Exception {
        City city = new City();
        city.setCityName("TestCity");
        city.setId(1L);
        Image image = new Image();
        image.setName("test.jpg");
        city.setImage(image);
        CityDto cityDto = new CityDto(city);

        given(cityService.getCityById(cityDto.getId())).willReturn(cityDto);

        mvc.perform(get("/cities/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(city.getId().intValue())));
    }

//    @Test
//    public void postCity() throws Exception {
//        City city = new City();
//        city.setCityName("TestCity");
//        Region region = new Region();
//        region.setRegionName("TestRegion");
//        MockMultipartFile file = new MockMultipartFile("file", "image.jpg",
//                MediaType.TEXT_PLAIN_VALUE, "test".getBytes(StandardCharsets.UTF_8));
//        Image image = new Image();
//        image.setName(file.getOriginalFilename());
//        CreateCityResource cityResource = new CreateCityResource(city.getCityName(), region.getRegionName(), image.getName(), true);
//
//        mvc.perform(multipart("/cities").file(file)
//                        .content(asJsonString(cityResource.getCityName()))
//                        .content(asJsonString(cityResource.getRegion()))
//                        .content(asJsonString(cityResource.getImage()))
//                        .content(asJsonString(cityResource.isTopDestination())))
//                .andExpect(status().isCreated());
//    }
}
