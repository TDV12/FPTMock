package fa.edu.vn.service.impl;

import fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto;
import fa.edu.vn.dto.dropDownDto.LocationDropDownDto;
import fa.edu.vn.entites.Location;
import fa.edu.vn.repository.ILocationRepository;
import fa.edu.vn.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements ILocationService {
    @Autowired
    private ILocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public List<DetailLocationDropDownDto> findAllLocationDetailByLocationId(Integer id) {
        return locationRepository.findAllLocationDetailByLocationId(id);
    }


    @Override
    public List<LocationDropDownDto> findLocationDropDownDtoName() {
        return locationRepository.findLocationDropDown();
    }

    @Override
    public boolean save(Location location) {
        return locationRepository.save(location) != null;
    }

    @Override
    public Location findById(Integer id) {
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find location"));
    }

}
