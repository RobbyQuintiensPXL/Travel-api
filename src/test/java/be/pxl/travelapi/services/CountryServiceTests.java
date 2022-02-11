package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CountryDto;
import be.pxl.travelapi.dto.CreateCountryResource;
import be.pxl.travelapi.models.Country;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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
        countryList.add(new Country());

        when(countryRepository.findAll()).thenReturn(countryList);

        List<CountryDto> countries = countryService.listAllCountries();
        assertTrue(countries.size() == 1);
    }

    @Test
    public void getCountryByName(){
        Country newCountry = new Country();
        newCountry.setCountryName("BobbejaanLand");

        when(countryRepository.findCountryByCountryName(newCountry.getCountryName())).thenReturn(Optional.of(newCountry));

        CountryDto countryDto = countryService.getCountryByName(newCountry.getCountryName());

        assertTrue(countryDto.getCountryName().equals(newCountry.getCountryName()));
    }

    @Test
    public void addCountry(){
        Country newCountry = new Country();
        when(countryRepository.save(any(Country.class))).thenReturn(new Country());

        CreateCountryResource countryResource = new CreateCountryResource(newCountry.getCountryName(), newCountry.getCountryCode());

        countryService.addCountry(countryResource);
    }
}
