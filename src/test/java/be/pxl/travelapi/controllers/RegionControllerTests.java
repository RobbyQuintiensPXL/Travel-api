package be.pxl.travelapi.controllers;

import be.pxl.travelapi.dto.CountryDto;
import be.pxl.travelapi.dto.CreateRegionResource;
import be.pxl.travelapi.dto.RegionDto;
import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.services.CountryService;
import be.pxl.travelapi.services.RegionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
@ActiveProfiles("test")
public class RegionControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @Test
    public void getAllRegions() throws Exception {
        Region region = new Region();
        region.setRegionName("TestRegion");

        List<RegionDto> regionList = Stream.of(region).map(RegionDto::new).collect(Collectors.toList());

        given(regionService.listAllRegions()).willReturn(regionList);

        mvc.perform(get("/regions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].regionName", is(region.getRegionName())));
    }

    @Test
    public void getRegionsByCountry() throws Exception{
        Country country = new Country();
        country.setCountryCode("TEST");
        Region region = new Region();
        region.setRegionName("TestRegion");
        region.setCountry(country);

        List<RegionDto> regionList = Stream.of(region).map(RegionDto::new).collect(Collectors.toList());

        given(regionService.listRegionsByCountry(country.getCountryCode())).willReturn(regionList);

        mvc.perform(get("/regions/country/TEST")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].regionName", is(region.getRegionName())))
        .andExpect(jsonPath("$[0].country.countryCode", is(region.getCountry().getCountryCode())));
    }

    @Test
    public void getRegionByName() throws Exception{
        Region region = new Region();
        region.setRegionName("TestRegion");

        RegionDto regionDto = new RegionDto(region);

        given(regionService.getRegionByName(region.getRegionName())).willReturn(regionDto);

        mvc.perform(get("/regions/TestRegion")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.regionName", is(region.getRegionName())));
    }

    @Test
    public void postRegion() throws Exception{
        Country country = new Country();
        country.setCountryCode("TEST");
        Region region = new Region();
        region.setRegionName("TestRegion");
        region.setCountry(country);

        CreateRegionResource regionResource = new CreateRegionResource(region.getRegionName(), region.getCountry().getCountryCode());

        mvc.perform(post("/regions")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(regionResource))
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    }

    //TODO make global method
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
