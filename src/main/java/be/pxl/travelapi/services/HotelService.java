package be.pxl.travelapi.services;

import be.pxl.travelapi.dto.CreateHotelResource;
import be.pxl.travelapi.dto.HotelDto;
import be.pxl.travelapi.exception.BusinessException;
import be.pxl.travelapi.models.City;
import be.pxl.travelapi.models.Hotel;
import be.pxl.travelapi.models.Image;
import be.pxl.travelapi.repository.CityRepository;
import be.pxl.travelapi.repository.HotelRepository;
import be.pxl.travelapi.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final ImageRepository imageRepository;
    private final FileStorageService storageService;

    private final static String NOT_FOUND = "] not found";

    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository, ImageRepository imageRepository, FileStorageService storageService) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.imageRepository = imageRepository;
        this.storageService = storageService;
    }

    public List<HotelDto> getAllHotels() {
        List<HotelDto> hotelDtoList = hotelRepository.findAll().stream().map(HotelDto::new).collect(Collectors.toList());
        if(hotelDtoList.isEmpty()){
            throw new BusinessException("No hotels found");
        }
        return hotelDtoList;
    }

    public List<HotelDto> getHotelsByCity(String cityName) {
        List<HotelDto> hotelDtoList = hotelRepository.findHotelsByCity_CityName(cityName).stream().map(HotelDto::new).collect(Collectors.toList());
        if(hotelDtoList.isEmpty()){
            throw new BusinessException("City [" + cityName + NOT_FOUND);
        }
        return hotelDtoList;
    }

    public List<HotelDto> getHotelsByStars(int stars) {
        List<HotelDto> hotelDtoList = hotelRepository.findHotelsByStars(stars).stream().map(HotelDto::new).collect(Collectors.toList());
        if(hotelDtoList.isEmpty()){
            throw new BusinessException("No hotels found with " + stars + " stars");
        }
        return hotelDtoList;
    }

    public HotelDto getHotelByName(String hotelName) {
        Optional<HotelDto> hotelDto = hotelRepository.findHotelByHotelName(hotelName).map(HotelDto::new);
        if(hotelDto.isEmpty()){
            throw new BusinessException("Hotel [" + hotelName + NOT_FOUND);
        }
        return hotelDto.get();
    }

    public List<HotelDto> getHotelByCityAndStars(String cityName, int stars){
        return hotelRepository.findHotelsByCity_CityNameAndStars(cityName, stars).stream().map(HotelDto::new).collect(Collectors.toList());
    }

    public void addHotel(CreateHotelResource hotelResource, MultipartFile image) throws IOException {
        Optional<Hotel> foundHotel = hotelRepository.findHotelByHotelNameAndCity_CityName(hotelResource.getHotelName(), hotelResource.getCity());
        if (foundHotel.isPresent()) {
            throw new BusinessException("Hotel [" + hotelResource.getHotelName() + "] already listed in [" + hotelResource.getCity() + "].");
        }
        Optional<City> foundCity = cityRepository.findCityByCityName(hotelResource.getCity());
        if (foundCity.isEmpty()) {
            throw new BusinessException("City [" + hotelResource.getCity() + NOT_FOUND);
        }

        Image newImage = new Image();
        newImage.setName(image.getOriginalFilename());

        imageRepository.save(newImage);

        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelResource.getHotelName());
        hotel.setStars(hotelResource.getStars());
        hotel.setAddress(hotelResource.getAddress());
        hotel.setCity(foundCity.get());
        hotel.setImageHotel(newImage);
        hotel.setImageRoomOne(hotelResource.getImageRoomOne().getBytes());
        hotel.setImageRoomTwo(hotelResource.getImageRoomTwo().getBytes());
        hotel.setTopHotel(hotelResource.isTopHotel());

        hotelRepository.save(hotel);
        storageService.save(image);
    }
}
