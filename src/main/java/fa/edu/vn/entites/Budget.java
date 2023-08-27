package fa.edu.vn.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer budgetId;

    @Column(name = "budget_code")
    private String budgetCode;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ClassBatch> classBatch;


    public Budget(String budgetCode) {
        this.budgetCode = budgetCode;
    }
}
