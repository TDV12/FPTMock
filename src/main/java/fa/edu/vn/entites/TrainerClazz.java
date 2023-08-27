package fa.edu.vn.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerClazz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @ToString.Exclude
    @JsonIgnore
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "clazz_batch")
    @ToString.Exclude
    @JsonIgnore
    private ClassBatch clazzBatch;
}
