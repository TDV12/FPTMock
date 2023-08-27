package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.FormatTypeDropDownDto;
import fa.edu.vn.entites.FormatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IFormatTypeReposity extends JpaRepository<FormatType, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.FormatTypeDropDownDto(p.formatTypeId,p.formatType) FROM FormatType p")
    List<FormatTypeDropDownDto> findFormatTypeDropDownDto();
}
