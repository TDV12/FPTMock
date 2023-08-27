package fa.edu.vn.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table
public class Topic {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer topicId;

    @Column
    private String remarks;

    @Column
    private String topicName;
    
    @OneToMany(mappedBy = "topic")
    private Set<MilestoneConfiguration> milestoneConfigurations;

}
