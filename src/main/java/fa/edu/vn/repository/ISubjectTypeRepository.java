package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.SubjectTypeDropDownDto;
import fa.edu.vn.entites.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubjectTypeRepository extends JpaRepository<SubjectType, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.SubjectTypeDropDownDto(p.subjectTypeId,p.subjectType) FROM " +
            "SubjectType p")
    List<SubjectTypeDropDownDto> findSubjectDropDownDto();
}
