package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterTrainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_trainer_id")
    private Integer masterTrainerId;

    @Column
    private String type;

    @Column
    private String remarks;

    @OneToMany(mappedBy = "masterTrainer")
    @ToString.Exclude
    private List<ClassBatch> classBatchList;

    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "master_trainer_profile")
    private TrainerProfile trainerProfile;

    public MasterTrainer(String type, String remarks) {
        this.type = type;
        this.remarks = remarks;
    }
}
