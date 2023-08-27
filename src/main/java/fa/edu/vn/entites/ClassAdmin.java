package fa.edu.vn.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class ClassAdmin {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_admin_id")
    private Integer classAdminId;

	@Column(name = "class_admin_name")
    private String adminName;

    @Column
    private String remarks;

    public ClassAdmin(String adminName, String remarks) {
        this.adminName = adminName;
        this.remarks = remarks;
    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "classAdmin")
    @ToString.Exclude
    @JsonIgnore
    private Set<ClassBatch> classBatch;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_admin_profile_id")
    @ToString.Exclude
    @JsonIgnore
    private ClassAdminProfile classAdminProfile;
}
