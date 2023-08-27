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
public class SubSubjectType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_subject_type_id")
    private Integer subSubjectTypeId;

    @Column
    private String remarks;

    @Column(name = "sub_subject_type_name")
    private String subSubjectType;

    @OneToMany(mappedBy = "subSubjectType")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public SubSubjectType(String subSubjectType) {
        this.subSubjectType = subSubjectType;
    }
}
