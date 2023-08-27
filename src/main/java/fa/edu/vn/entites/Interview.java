package fa.edu.vn.entites;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
public class Interview {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "interviewID")
	    private Integer interviewId;

	    @Column
	    private String time;

	    @Column
	    private LocalDate date;

	    @Column
	    private String interviewer;

	    @Column
	    private String comments;

	    @Column
	    private String result;

	    @Column
	    private String remarks;
	    
	    @ManyToOne
	    @JoinColumn(name = "candidate_id")
	    private Candidate candidate;
}
