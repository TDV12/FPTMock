package fa.edu.vn.entites;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Gpa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpa_id")
    private Integer gpaId;



    @Column(name = "gpa_result")
    private double gpaResult;

    @Column
    private String remarks;

    @Column(name = "gpa_gpa_id")
    private Integer gpaGpaId;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "GPA")
    @ToString.Exclude
    private Trainee trainee;
}
