package fa.edu.vn.repository;

import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.dto.dropDownDto.ClassNameDropDownDto;
import fa.edu.vn.entites.ClassBatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClassRepository extends JpaRepository<ClassBatch, Integer>, JpaSpecificationExecutor<ClassBatch> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.ClassNameDropDownDto(p.classNameId,p.className) From ClassName p")
    List<ClassNameDropDownDto> findAllClassName();

    @Query(value = " SELECT new fa.edu.vn.dto.ClassListingDto(p.classId,p.classCode,p.className.className," +
            "p.actualStartDate,p.actualEndDate,p.location.locationName,p.status) FROM ClassBatch p")
    List<ClassListingDto> findClassList();

    @Query(value = "SELECT count(p.classId) FROM ClassBatch p LEFT JOIN p.className c WHERE c.classNameId=?1")
    Integer findMaxIdByClassNameId(Integer id);

    ClassBatch findClassBatchByClassCode(String code);

    @Query(value = "SELECT max(classId) FROM ClassBatch ")
    Integer findMaxClassBatchId();

    @Query(value = " SELECT new fa.edu.vn.dto.ClassListingDto(p.classId,p.classCode,p.className.className," +
            "p.actualStartDate,p.actualEndDate,p.location.locationName,p.status) FROM ClassBatch p")
    Page<ClassListingDto> findClassListPage(Pageable pageable);

}
