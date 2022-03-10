package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CountryDto;
import be.pxl.travelapi.dto.CreateCountryResource;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CountryRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CountryServiceTests {

    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @Test
    public void getAllCountries(){
        List<Country> countryList = new LinkedList<>();
        Country country = new Country();
        country.setRegionList(new ArrayList<Region>());
        countryList.add(country);

        when(countryRepository.findAll()).thenReturn(countryList);

        List<CountryDto> countries = countryService.listAllCountries();
        assertEquals(1, countries.size());
    }

    @Test
    public void getCountryByName(){
        Country newCountry = new Country();
        newCountry.setCountryName("BobbejaanLand");
        newCountry.setRegionList(new ArrayList<Region>());

        when(countryRepository.findCountryByCountryName(newCountry.getCountryName())).thenReturn(Optional.of(newCountry));

        CountryDto countryDto = countryService.getCountryByName(newCountry.getCountryName());

        assertEquals(countryDto.getCountryName(), newCountry.getCountryName());
    }

    @Test
    public void addCountry(){
        Country newCountry = new Country();
        when(countryRepository.save(any(Country.class))).thenReturn(new Country());

        CreateCountryResource countryResource = new CreateCountryResource(newCountry.getCountryName(), newCountry.getCountryCode());

        countryService.addCountry(countryResource);
    }

    @Test
    public void throwExceptionCountryNameNotFound(){
        Throwable thrown = assertThrows(BusinessException.class, () -> countryService.getCountryByName("test"));
        assertEquals("Country [test] not found", thrown.getMessage());
    }

//    @Test
//    public void throwExceptionCountriesNotFound(){
//        CountryService countryServiceMock = mock(CountryService.class);
//        assertThrows(BusinessException.class, () -> countryServiceMock.listAllCountries());
//    }
}
