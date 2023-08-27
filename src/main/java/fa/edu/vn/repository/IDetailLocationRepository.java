package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto;
import fa.edu.vn.entites.DetailLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IDetailLocationRepository extends JpaRepository<DetailLocation, Integer> {

    Optional<DetailLocation> findDetailLocationById(Integer id);

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto(p.id,p.detailLocation) FROM DetailLocation p")
    List<DetailLocationDropDownDto> findDetailLocationDropDownDto();


}
