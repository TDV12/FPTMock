package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.ClassAdminDropDownDto;
import fa.edu.vn.entites.ClassAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IClassAdminRepository extends JpaRepository<ClassAdmin, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.ClassAdminDropDownDto(p.classAdminId,p.adminName) FROM ClassAdmin" +
            " p")
    List<ClassAdminDropDownDto> findAllClassAdminDropDownDto();
}
