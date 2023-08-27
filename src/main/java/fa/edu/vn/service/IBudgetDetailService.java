package fa.edu.vn.service;

import fa.edu.vn.dto.BudgetDto;
import fa.edu.vn.entites.Budget;
import fa.edu.vn.entites.BudgetDetail;

import java.util.List;

public interface IBudgetDetailService {
    void deleteAllBudgetDetail(List<BudgetDetail> budgetDetailList);

    List<BudgetDetail> findAllBudgetDetailById(List<Integer> id);

    List<BudgetDetail> findAllBudgetDetailByStatusAndClassId(String status,Integer id);

    List<BudgetDto> mapAllBudgetDtoByStatusAndClassId(String status,Integer id);

}
