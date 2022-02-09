package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CreateRegionResource;
import be.pxl.travelapi.dto.RegionDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.Country;
import be.pxl.travelapi.models.Region;
import be.pxl.travelapi.repository.CountryRepository;
import be.pxl.travelapi.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<RegionDto> listAllRegions(){
        return regionRepository.findAll().stream().map(RegionDto::new).collect(Collectors.toList());
    }

    public List<RegionDto> listRegionsByCountry(String countryCode){
        return regionRepository.findRegionsByCountry_CountryCode(countryCode).stream().map(RegionDto::new).collect(Collectors.toList());
    }

    public RegionDto getRegionByName(String regionName){
        return regionRepository.findRegionByRegionName(regionName).map(RegionDto::new).get();
    }

    public void addRegion(CreateRegionResource regionResource){
        Optional<Region> foundRegion = regionRepository.findRegionByRegionName(regionResource.getRegionName());
        if(foundRegion.isPresent()){
            throw new BusinessException("Region [" + regionResource.getRegionName() + "] already listed.");
        }

        Optional<Country> country = countryRepository.findCountryByCountryCode(regionResource.getCountryCode());
        if(country.isEmpty()){
            throw new BusinessException("Country [" + regionResource.getRegionName() + "] not found.");
        }

        Region region = new Region();
        region.setRegionName(regionResource.getRegionName());
        region.setCountry(country.get());

        regionRepository.save(region);
    }
}
