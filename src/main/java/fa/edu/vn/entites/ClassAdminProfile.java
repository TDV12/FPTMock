package fa.edu.vn.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ClassAdminProfile {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_admin_profile_id")
    private Integer classAdminProfileId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column
    private Integer gender;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Column
    private String remarks;

    public ClassAdminProfile(String fullName, LocalDate dateOfBirth, Integer gender, String phone, String email, String remarks,ClassAdmin classAdmin) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.remarks = remarks;
        this.classAdmin = classAdmin;
    }

    @OneToOne(mappedBy = "classAdminProfile")
    @ToString.Exclude
    @JsonIgnore
    private ClassAdmin classAdmin;
	
	
}
