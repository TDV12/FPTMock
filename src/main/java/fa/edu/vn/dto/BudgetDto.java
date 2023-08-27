package fa.edu.vn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDto {
    private Integer budgetDetailId;
    private String budgetItem;
    private String budgetUnit;
    private Integer budgetExpense;
    private Integer budgetQuantity;
    private Float budgetAmount;
    private Integer budgetTax;
    private Float budgetSum;
    private String budgetNote;
//    private String budgetCode;
}
