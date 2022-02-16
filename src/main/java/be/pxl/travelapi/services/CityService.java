package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CityDto;
import be.pxl.travelapi.dto.CreateCityResource;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CityRepository;
import be.pxl.travelapi.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegionRepository regionRepository;

    private final static String NOT_FOUND = "] not found";

    public List<CityDto> getAllCities(){
        return cityRepository.findAll().stream().map(CityDto::new).collect(Collectors.toList());
    }

    public List<CityDto> getAllCitiesByRegion(String regionName){
        List<CityDto> cityDtoList = cityRepository.findCitiesByRegion_RegionName(regionName).stream().map(CityDto::new).collect(Collectors.toList());
        if(cityDtoList.isEmpty()){
            throw new BusinessException("Region [" + regionName + NOT_FOUND);
        }
        return cityDtoList;
    }

    public CityDto getCityByName(String cityName){
        Optional<CityDto> cityDto = cityRepository.findCityByCityName(cityName).map(CityDto::new);
        if(cityDto.isEmpty()){
            throw new BusinessException("City [" + cityName + NOT_FOUND);
        }
        return cityDto.get();
    }

    public CityDto getCityById(Long id){
        Optional<CityDto> cityDto = cityRepository.findCityById(id).map(CityDto::new);
        if(cityDto.isEmpty()){
            throw new BusinessException("City with id [" + id + NOT_FOUND);
        }
        return cityDto.get();
    }

    public void addCity(CreateCityResource cityResource) throws IOException {
        Optional<Region> foundRegion = regionRepository.findRegionByRegionName(cityResource.getRegion());
        if(foundRegion.isEmpty()){
            throw new BusinessException("Region [" + cityResource.getRegion() + NOT_FOUND);
        }

        Optional<City> foundCity = cityRepository.findCityByCityName(cityResource.getCityName());
        if(foundCity.isPresent()){
            throw new BusinessException("City [" + cityResource.getCityName() + "] already listed.");
        }

        City city = new City();
        city.setCityName(cityResource.getCityName());
        city.setRegion(foundRegion.get());
        city.setImage(cityResource.getImage().getBytes());
        city.setTopDestination(cityResource.isTopDestination());

        cityRepository.save(city);
    }
}
