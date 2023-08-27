package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto;
import fa.edu.vn.dto.dropDownDto.LocationDropDownDto;
import fa.edu.vn.entites.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ILocationRepository extends JpaRepository<Location, Integer> {

    @Query(value = "Select new fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto(m.id,m.detailLocation) From " +
            "Location p left join p.detailLocation m where p.locationId=?1")
    List<DetailLocationDropDownDto> findAllLocationDetailByLocationId(Integer id);

    @Query(value = "SELECT p.locationName FROM Location p")
    List<String> allLocationName();

    Optional<Location> findById(Integer id);



    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.LocationDropDownDto(p.locationId,p.locationName) FROM " +
            "Location p")
    List<LocationDropDownDto> findLocationDropDown();

	Location getByLocationId(Integer locationId);

}
