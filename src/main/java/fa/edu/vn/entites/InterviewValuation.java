package fa.edu.vn.entites;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
public class InterviewValuation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_valuation_id")
    private Integer interviewValuationId;

    @Column
    private LocalDate date;

    @Column
    private String interviewer;

    @Column
    private String remarks;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

}
