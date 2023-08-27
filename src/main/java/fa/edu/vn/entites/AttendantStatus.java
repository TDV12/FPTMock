package fa.edu.vn.entites;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

import javax.persistence.*;


@Entity
@Data
@Table
public class AttendantStatus {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendant_id")
    private Integer attendantId;

    @Column(name = "discipline_point")
    private double disciplinePoint;

    @Column
    private Integer milestone;

    @Column
    private String remarks;
    

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "AttendantStatus")
    @ToString.Exclude
    private Set<Trainee> trainees;

}
