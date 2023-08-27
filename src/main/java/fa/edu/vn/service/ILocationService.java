package fa.edu.vn.service;

import fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto;
import fa.edu.vn.dto.dropDownDto.LocationDropDownDto;
import fa.edu.vn.entites.Location;

import java.util.List;

public interface ILocationService {
    List<Location> findAll();

    List<DetailLocationDropDownDto> findAllLocationDetailByLocationId(Integer id);

    List<LocationDropDownDto> findLocationDropDownDtoName();

    boolean save(Location location);

    Location findById(Integer id);

}
