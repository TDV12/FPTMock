package fa.edu.vn.entites;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@Table
public class MilestoneConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "milestone_configuration_id")
    private Integer milestoneConfigurationId;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_id")
    @ToString.Exclude
    private Trainee trainee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    @ToString.Exclude
    private Topic topic;
 
}
