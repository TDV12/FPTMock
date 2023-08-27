package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.BudgetDropDownDto;
import fa.edu.vn.entites.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBudgetRepository extends JpaRepository<Budget, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.BudgetDropDownDto(p.budgetId,p.budgetCode) FROM Budget p")
    List<BudgetDropDownDto> findBudgetDropDownDto();
}
