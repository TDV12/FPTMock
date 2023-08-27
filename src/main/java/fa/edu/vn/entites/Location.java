package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;


    @Column(columnDefinition = "NVARCHAR(50)")
    private String locationName;

    @Column
    private String acronym;

    public Location(String locationName, String acronym, List<DetailLocation> detailLocation) {
        this.locationName = locationName;
        this.acronym = acronym;
        this.detailLocation = detailLocation;
    }

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DetailLocation> detailLocation;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    @OneToMany(mappedBy = "Channel",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Candidate> candidates;

}
