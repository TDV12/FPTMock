package fa.edu.vn.entites;

import fa.edu.vn.enums.ClassStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Integer classId;

    @ManyToOne
    @JoinColumn(name = "class_name")
    @ToString.Exclude
    private ClassName className;

    @Column(name = "class_code", unique = true)
    private String classCode;

    @Column(name = "expected_start_date", columnDefinition = "date")
    private LocalDate exceptedStartDate;

    @Column(name = "expected_end_date", columnDefinition = "date")
    private LocalDate exceptedEndDate;

    @Column(name = "planned_trainee_number")
    private Integer plannedTrainee;

    @Column(name = "estimated_budget")
    private Float estimatedBudget;

    @Column(name = "total_budget")
    private Float total;

    @Column(name = "over_budget")
    private Float overBudget;

    @Column(name = "actual_start_date", columnDefinition = "date")
    private LocalDate actualStartDate;

    @Column(name = "actual_end_date", columnDefinition = "date")
    private LocalDate actualEndDate;

    @Column(name = "accepter_trainee_number")
    private Integer acceptedTrainee;

    @Column(name = "actual_trainee_number")
    private Integer actualTrainee;

    @Column(name = "curriculum")
    private String curriculum;

    @Column
    private String history;

    @Column
    @Enumerated(EnumType.STRING)
    private ClassStatusEnum status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "budget_id")
    @ToString.Exclude
    private Budget budget;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classBatch")
    @ToString.Exclude
    private List<BudgetDetail> detaiBudget;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "subject_type_id")
    private SubjectType subjectType;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "deliveryt_type_id")
    private DeliveryType deliveryType;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "sub_subject_type_id")
    private SubSubjectType subSubjectType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "format_type_id")
    @ToString.Exclude
    private FormatType formatType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scope_id")
    @ToString.Exclude
    private Scope scope;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_partner_id")
    @ToString.Exclude
    private SupplierPartner supplierPartner;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @ToString.Exclude
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "master_trainer_id")
    private MasterTrainer masterTrainer;

    @OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Audit> audit;

    @ManyToOne
    @JoinColumn(name = "class_admin_id")
    @ToString.Exclude
    private ClassAdmin classAdmin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "learning_path_id")
    @ToString.Exclude
    private LearningPath learningPath;

    @OneToMany(mappedBy = "classBatch", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Trainee> trainees;

    @ManyToOne
    @JoinColumn(name = "detail_location_id")
    @ToString.Exclude
    private DetailLocation detailLocation;

    @Override
    public String toString() {
        return "ClassBatch{" +
                "classId=" + classId +
                ", className=" + className +
                ", classCode='" + classCode + '\'' +
                ", exceptedStartDate=" + exceptedStartDate +
                ", exceptedEndDate=" + exceptedEndDate +
                ", plannedTrainee=" + plannedTrainee +
                ", estimatedBudget=" + estimatedBudget +
                ", total=" + total +
                ", overBudget=" + overBudget +
                ", actualStartDate=" + actualStartDate +
                '}';
    }
}
