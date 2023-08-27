package fa.edu.vn.entites;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer classNameId;

    private String className;

    @OneToMany(mappedBy = "className")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public ClassName(String className) {
        this.className = className;
    }
}
