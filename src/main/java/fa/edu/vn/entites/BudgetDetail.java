package fa.edu.vn.entites;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer budgetDetailId;

    @Column(name = "budget_unit")
    private String budgetUnit;

    @Column(name = "budget_expense")
    private Integer budgetExpense;

    @Column(name = "budget_quantity")
    private Integer budgetQuantity;

    @Column(name = "budget_amount")
    private Float budgetAmount;

    @Column(name = "budget_tax")
    private Integer budgetTax;

    @Column(name = "budget_sum")
    private Float budgetSum;

    @Column(name = "budget_note")
    private String budgetNote;

    @Column(name = "budget_item")
    private String budgetItem;

    @Column(name = "delete_flag")
    private String deleteFlag;

    @ManyToOne
    @JoinColumn(name = "class_batch_id")
    @ToString.Exclude
    private ClassBatch classBatch;


}
