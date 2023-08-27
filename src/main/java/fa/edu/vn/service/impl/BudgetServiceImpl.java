package fa.edu.vn.service.impl;

import fa.edu.vn.dto.dropDownDto.BudgetDropDownDto;
import fa.edu.vn.entites.Budget;
import fa.edu.vn.repository.IBudgetRepository;
import fa.edu.vn.service.IBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements IBudgetService {
    @Autowired
    private IBudgetRepository budgetRepository;

    @Override
    public void removeAllBugdet(List<Budget> budgets) {
        budgetRepository.deleteAll(budgets);

    }

    @Override
    public void removeByBudgetCode(List<Integer> id) {
        budgetRepository.deleteAllById(id);
    }

    @Override
    public List<BudgetDropDownDto> findBudgetDropDownDto() {
        return budgetRepository.findBudgetDropDownDto();
    }

    @Override
    public Budget findBudgetById(Integer id) {
        return budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't Find Budget Code"));
    }
}
