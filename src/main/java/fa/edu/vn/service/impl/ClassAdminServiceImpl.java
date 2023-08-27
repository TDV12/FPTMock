package fa.edu.vn.service.impl;

import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.dto.dropDownDto.ClassAdminDropDownDto;
import fa.edu.vn.entites.ClassAdmin;
import fa.edu.vn.repository.IClassAdminRepository;
import fa.edu.vn.service.IClassAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassAdminServiceImpl implements IClassAdminService {

    @Autowired
    private IClassAdminRepository classAdminRepository;


    @Override
    public List<ClassAdminDropDownDto> findAllClassAdminDropDownDto() {
        return classAdminRepository.findAllClassAdminDropDownDto();
    }

    @Override
    public ClassAdmin findClassAdminById(Integer id) {
        return classAdminRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find Class Admin"));
    }

}
