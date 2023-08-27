package fa.edu.vn.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class FormatType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "format_type_id")
    private Integer formatTypeId;

    @Column(name = "format_type_name")
    private String formatType;
    @Column
    private String remarks;

    @OneToMany(mappedBy = "formatType")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public FormatType(String formatType) {
        this.formatType = formatType;
    }
}
