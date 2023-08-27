package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.ClassNameDropDownDto;
import fa.edu.vn.entites.ClassName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClassNameRepository extends JpaRepository<ClassName, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.ClassNameDropDownDto(p.classNameId,p.className) FROM ClassName p")
    List<ClassNameDropDownDto> findClassNameDropDownDto();
}
