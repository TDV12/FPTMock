package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class SubjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectTypeId;

    @Column
    private String remarks;

    @Column(name = "subject_type_name")
    private String subjectType;

    @OneToMany(mappedBy = "subjectType")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public SubjectType(String subjectType) {
        this.subjectType = subjectType;
    }
}
