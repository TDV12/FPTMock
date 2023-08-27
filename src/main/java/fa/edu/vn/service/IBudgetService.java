package fa.edu.vn.service;

import fa.edu.vn.dto.dropDownDto.BudgetDropDownDto;
import fa.edu.vn.entites.Budget;

import java.util.List;

public interface IBudgetService {

    void removeAllBugdet(List<Budget> budgets);

    void removeByBudgetCode(List<Integer> id);

    List<BudgetDropDownDto>findBudgetDropDownDto();

    Budget findBudgetById(Integer id);
}
