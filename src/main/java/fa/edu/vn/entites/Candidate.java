package fa.edu.vn.entites;

import lombok.Data;
import lombok.ToString;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
public class Candidate {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empl_id")
    private Integer emplId;

    @Column(name = "application_date")
    private LocalDate applicationDate;

    @Column
    private String account;

    @Column
    private String status;

    @Column
    private String remarks;
    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "trainee_candidate_profile_id")
    private TraineeCandidateProfile traineeCandidateProfile;
    
    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Interview> interviews;
    
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "channel_id")
    private Channel Channel;
    
    @Column
    private String history;
    
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "location_id")
    private Location location;
    
    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<EntryTest> EntryTests;
}
