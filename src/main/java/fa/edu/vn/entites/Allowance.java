package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Allowance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="[group]")
	private Integer group;

	@Column
	private double allowanceResult;

	@Column
	private String remarks;
	
	 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "allowance_group_group")
	@ToString.Exclude
    private AllowanceGroup allowanceGroup;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_id")
	@ToString.Exclude
    private Trainee trainee;
}
