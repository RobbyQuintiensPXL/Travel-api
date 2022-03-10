package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CountryDto;
import be.pxl.travelapi.dto.CreateCountryResource;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<CountryDto> listAllCountries() {
        List<CountryDto> countryDtoList = countryRepository.findAll().stream().map(CountryDto::new).collect(Collectors.toList());
        if(countryDtoList.isEmpty()){
            throw new BusinessException("No countries found");
        }
        return countryDtoList;
    }

    public CountryDto getCountryByName(String countryName) {
        Optional<CountryDto> foundCountry = countryRepository.findCountryByCountryName(countryName).map(CountryDto::new);
        if (foundCountry.isEmpty()) {
            throw new BusinessException("Country [" + countryName + "] not found");
        }
        return foundCountry.get();
    }

    public void addCountry(CreateCountryResource countryResource) {
        Optional<Country> foundCountry = countryRepository.findCountryByCountryName(countryResource.getCountryName());
        if (foundCountry.isPresent()) {
            throw new BusinessException("Country [" + countryResource.getCountryName() + "] already listed.");
        }
        Country country = new Country();
        country.setCountryName(countryResource.getCountryName());
        country.setCountryCode(countryResource.getCountryCode());
        countryRepository.save(country);
    }
}
