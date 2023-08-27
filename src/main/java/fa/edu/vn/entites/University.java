package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id")
    private Integer universityId;

    @Column(name = "university_name", unique = true, columnDefinition = "NVARCHAR(255)")
    private String universityName;

    @Column
    private Integer remarks;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<TraineeCandidateProfile> traineeCandidateProfiles;

    public University(String universityName) {
        this.universityName = universityName;
    }

}
