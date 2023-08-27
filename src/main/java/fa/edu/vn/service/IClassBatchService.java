package fa.edu.vn.service;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.dto.dropDownDto.ClassNameDropDownDto;
import fa.edu.vn.entites.ClassBatch;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

public interface IClassBatchService {

    ClassBatch save(ClassDto classDto, Principal principal);

    ClassBatch save(ClassBatch classBatch);

    List<ClassNameDropDownDto> findAllClassNames();

    List<ClassListingDto> findClassListing();

    ClassBatch findClassDetailByCode(String code);

    Integer findMaxClassBatchIdByClassNameId(Integer id);

    ClassBatch findClassBatchById(Integer id);

    Resource getFileResource(String fileName);

    String appendDataForViewMode(ClassBatch classBatch, Model model);

    Page<ClassListingDto> pageClass(Pageable pageAble);

    Page<ClassBatch> findPageClassByCondition(Integer location, Integer className, String status, String fromDate, String toDate, Pageable pageable);

    void appendDataPage(Model model,Page<ClassListingDto> classListingDtoPage,int page, int size);
}
