package fa.edu.vn.entites;

import fa.edu.vn.enums.TraineeStatusEnum;
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
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainee_candidate_id")
    private Integer traineeCandidateId;

    @Column(name = "[group]")
    private String group;

    @Column
    private String remarks;
    
    @Column
    private String history;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_candidate_profile_id")
    @ToString.Exclude
    private TraineeCandidateProfile traineeCandidateProfile;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @ToString.Exclude
    private ClassBatch classBatch;

    @Column(name = "trainee_status")
    @Enumerated(EnumType.STRING)
    private TraineeStatusEnum status;

    @OneToMany
    @JoinColumn(name = "allowance_id")
    @ToString.Exclude
    private Set<Allowance> allowances;

    @OneToMany(mappedBy = "trainee")
    @ToString.Exclude
    private Set<MilestoneConfiguration> milestoneConfigurations;

    @OneToOne
    @JoinColumn(name = "gpa_id")
    private Gpa GPA;

    @ManyToOne
    @JoinColumn(name = "reward_penalty_id")
    @ToString.Exclude
    private RewardPenalty rewardPenalty;

    @ManyToOne
    @JoinColumn(name = "guarantee_id")
    @ToString.Exclude
    private Guarantee guarantee;

    @ManyToOne
    @JoinColumn(name = "attendant_status_id")
    private AttendantStatus AttendantStatus;

    @OneToMany(mappedBy = "trainee")
    @ToString.Exclude
    private Set<InterviewValuation> InterviewValuations;

}
