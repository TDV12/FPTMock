package fa.edu.vn.entites;

import lombok.Data;

import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Table
public class Offer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id")
    private Integer offerId;

    @Column(name = "job_rank")
    private Integer jobRank;

    @Column
    private String technology;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "offer_salary")
    private double offerSalary;

    @Column
    private String remarks;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "offer")
    private Set<Candidate> candidates;

}
