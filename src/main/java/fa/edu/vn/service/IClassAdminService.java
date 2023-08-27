package fa.edu.vn.service;

import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.dto.dropDownDto.ClassAdminDropDownDto;
import fa.edu.vn.entites.ClassAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClassAdminService {

    List<ClassAdminDropDownDto> findAllClassAdminDropDownDto();

    ClassAdmin findClassAdminById(Integer id);



}
