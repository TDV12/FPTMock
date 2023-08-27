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
public class Faculty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Integer facultyId;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column
    private String remarks;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<TraineeCandidateProfile> traineeCandidateProfile;

    public Faculty(String facultyName) {
        this.facultyName = facultyName;
    }


}
