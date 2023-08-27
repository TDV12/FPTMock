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
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Integer trainerId;

    @Column
    private String type;

    @Column
    private String remarks;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_profile_id")
    private TrainerProfile trainerProfile;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public Trainer(String type, String remarks) {
        this.type = type;
        this.remarks = remarks;
    }


}
