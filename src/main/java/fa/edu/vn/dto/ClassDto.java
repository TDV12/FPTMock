package fa.edu.vn.dto;

import fa.edu.vn.customAnnotation.DateValid;
import fa.edu.vn.entites.Audit;
import fa.edu.vn.entites.Budget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDto {

    @NotBlank(message = "Class code not blank")
    private String classCode;

    @NotNull(message = "Class name not blank")
    private Integer className;

    @NotBlank(message = "Status not blank")
    private String status;

    private Integer plannedTrainee;

    private Integer acceptedTrainee;

    private Integer actualTrainee;

    @NotBlank(message = "Excepted start date not blank")
    @DateValid
    private String exceptedStartDate;

    @DateValid
    @NotBlank(message = "Excepted end date not blank")
    private String exceptedEndDate;

    @NotNull(message = "Location not blank")
    private Integer locationId;

    @NotNull(message = "Detail location not blank")
    private Integer detailLocation;

    @NotNull(message = "Budget code not blank")
    private Integer budgetCode;

    @NotNull(message = "Estimate Budget not null")
    private Float estimatedBudget;

    @NotNull(message = "Class admin not blank")
    private Integer classAdmin;

    private String history;

    private MultipartFile learningPath;

    private Integer subjectType;

    private Integer subSubjectType;

    private Integer deliveryType;

    private Integer formatType;

    private Integer scope;

    private Integer supplierPartner;

    @DateValid
    private String actualStartDate;

    @DateValid
    private String actualEndDate;

    private Integer masterTrainer;

    private Integer trainer;

    private String curriculum;

    private String remarks;

    private Float total;

    private Float overBudget;

    private List<BudgetDto> budgets;

    private List<AuditDto> audits;

}
