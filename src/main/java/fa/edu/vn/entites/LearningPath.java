package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class LearningPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learning_path_id")
    private Integer learningPathId;

    @Column(name = "learning_path_name")
    private String learningPathName;

    @Column(name = "learning_path_original_name")
    private String learningPathOriginalName;

    @OneToOne(mappedBy = "learningPath", cascade = CascadeType.ALL)
    @ToString.Exclude
    private ClassBatch classBatch;

    public LearningPath(String learningPathName, String learningPathOriginalName) {
        this.learningPathName = learningPathName;
        this.learningPathOriginalName = learningPathOriginalName;
    }
}
