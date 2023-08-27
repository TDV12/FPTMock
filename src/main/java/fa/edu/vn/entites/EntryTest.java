package fa.edu.vn.entites;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
public class EntryTest {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "test_id")
	    private Integer testId;

	    @Column
	    private String time;

	    @Column(columnDefinition = "date")
	    private LocalDate date;

	    @Column(name = "language_valuator")
	    private String languageValuator;

	    @Column(name = "language_result")
	    private double languageResult;

	    @Column(name = "technical_valuator")
	    private String technicalValuator;

	    @Column(name = "technical_result")
	    private double technicalResult;

	    @Column
	    private String result;

	    @Column
	    private String remarks;
	    
	    @ManyToOne
	    @JoinColumn(name = "candidate_id")
	    private Candidate candidate;

}
