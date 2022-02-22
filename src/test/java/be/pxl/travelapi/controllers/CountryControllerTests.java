package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CountryDto;
import be.pxl.travelapi.dto.CreateCountryResource;
import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CountryRepository;
import be.pxl.travelapi.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
@ActiveProfiles("test")
public class CountryControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryService countryService;

    @Test
    public void getAllCountries() throws Exception {
        Country country = new Country();
        country.setCountryCode("TEST");
        country.setCountryName("TestCountry");
        country.setRegionList(new ArrayList<Region>());

        List<CountryDto> countryList = Stream.of(country).map(CountryDto::new).collect(Collectors.toList());

        given(countryService.listAllCountries()).willReturn(countryList);

        mvc.perform(get("/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].countryCode", is(country.getCountryCode())));
    }

    @Test
    public void getCountryByName() throws Exception {
        Country country = new Country();
        country.setCountryCode("TEST");
        country.setCountryName("TestCountry");
        country.setRegionList(new ArrayList<Region>());

        CountryDto countryDto = new CountryDto(country);

        given(countryService.getCountryByName(country.getCountryName())).willReturn(countryDto);

        mvc.perform(get("/countries/TestCountry")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryCode", is(country.getCountryCode())));
    }

    @Test
    public void postCountry() throws Exception {
        Country country = new Country();
        country.setCountryCode("TEST");
        country.setCountryName("TestCountry");
        country.setRegionList(new ArrayList<Region>());
        CreateCountryResource countryResource = new CreateCountryResource(country.getCountryCode(), country.getCountryName());

        mvc.perform(post("/countries")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(countryResource))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    }

    //TODO returnRegionsTest

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
