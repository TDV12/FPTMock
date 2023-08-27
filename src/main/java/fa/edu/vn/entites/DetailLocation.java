package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String detailLocation;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @ToString.Exclude
    private Location location;

    @OneToMany(mappedBy = "detailLocation")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public DetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }
}
