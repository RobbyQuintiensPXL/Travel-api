package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CityDto;
import be.pxl.travelapi.dto.CreateCityResource;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CityRepository;
import be.pxl.travelapi.repository.ImageRepository;
import be.pxl.travelapi.repository.RegionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;
    private final ImageRepository imageRepository;
    private final FileStorageService storageService;

    private final static String NOT_FOUND = "] not found";

    public CityService(CityRepository cityRepository, RegionRepository regionRepository, ImageRepository imageRepository, FileStorageService storageService) {
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
        this.imageRepository = imageRepository;
        this.storageService = storageService;
    }

    public List<CityDto> getAllCities(){
        List<CityDto> cityDtoList = cityRepository.findAll().stream().map(CityDto::new).collect(Collectors.toList());
        if(cityDtoList.isEmpty()){
            throw new BusinessException("No cities found");
        }
        return cityDtoList;
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

    public void addCity(CreateCityResource cityResource, MultipartFile image) throws IOException {
        Optional<Region> foundRegion = regionRepository.findRegionByRegionName(cityResource.getRegion());
        if(foundRegion.isEmpty()){
            throw new BusinessException("Region [" + cityResource.getRegion() + NOT_FOUND);
        }

        Optional<City> foundCity = cityRepository.findCityByCityName(cityResource.getCityName());
        if(foundCity.isPresent()){
            throw new BusinessException("City [" + cityResource.getCityName() + "] already listed.");
        }

        Image newImage = new Image();
        newImage.setName(image.getOriginalFilename());

        imageRepository.save(newImage);

        City city = new City();
        city.setCityName(cityResource.getCityName());
        city.setRegion(foundRegion.get());
        city.setImage(newImage);
        city.setTopDestination(cityResource.isTopDestination());

        cityRepository.save(city);
        storageService.save(image);
    }
}
