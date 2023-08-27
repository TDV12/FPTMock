package fa.edu.vn.entites;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Table
public class RewardPenalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_penalty_id")
    private Integer rewardPenaltyId;

    @Column(name = "milestones")
    private Integer milestones;

    @Column(name = "point")
    private Integer point;

    @Column(name = "comments")
    private String comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rewardPenalty")
    @ToString.Exclude
    private Set<Trainee> trainees;
}
