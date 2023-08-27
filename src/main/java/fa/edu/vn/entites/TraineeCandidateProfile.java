package fa.edu.vn.entites;

import fa.edu.vn.enums.AllocationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeCandidateProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainee_candidate_profile_id")
    private Integer traineeCandidateProfileId;

    @Column(name = "full_name", columnDefinition = "NVARCHAR(255)")
    private String fullName;

    @Column(name = "date_of_birth", columnDefinition = "date")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "graduation_year", columnDefinition = "date")
    private LocalDate graduationYear;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Column
    private String type;

    @Column
    private String skill;

    @Column(name = "foreign_language")
    private String foreignLanguage;

    @Column
    private String level;

    @Column
    private String account;

    @Column
    private String cv;

    @Column(name = "allocation_status")
    @Enumerated(EnumType.STRING)
    private AllocationStatusEnum allocationStatus;

    @Column
    private String remarks;

    @OneToOne
    @JoinColumn(name = "trainee_id")
    @ToString.Exclude
    private Trainee trainee;

    @OneToOne(mappedBy = "traineeCandidateProfile")
    @ToString.Exclude
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @ToString.Exclude
    private University university;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @ToString.Exclude
    private Faculty faculty;
}
