package fa.edu.vn.entites;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Table
public class Guarantee {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guarantee_id")
    private Integer guaranteeId;

    @Column
    private Integer psu;

    @Column
    private String comments;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "guarantee")
    @ToString.Exclude
    private Set<Trainee> trainees;

}
