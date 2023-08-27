package fa.edu.vn.mapper;

import fa.edu.vn.dto.BudgetDto;
import fa.edu.vn.entites.Budget;
import fa.edu.vn.entites.BudgetDetail;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BudgetMapper extends EntitiesMapper<BudgetDetail, BudgetDto> {
}
