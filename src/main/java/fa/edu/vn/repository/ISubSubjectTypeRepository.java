package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.SubSubjectTypeDropDownDto;
import fa.edu.vn.entites.SubSubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubSubjectTypeRepository extends JpaRepository<SubSubjectType, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.SubSubjectTypeDropDownDto(p.subSubjectTypeId,p.subSubjectType) FROM " +
            "SubSubjectType p")
    List<SubSubjectTypeDropDownDto> findSubSubjectTypeDropDownDto();
}
