package fa.edu.vn.repository;

import fa.edu.vn.dto.ClassListingDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClassRepositoryCustom {

    List<ClassListingDto> searchClassListingDto(Integer locationId, Integer classNameId, String status, String fromDate, String toDate);


}
