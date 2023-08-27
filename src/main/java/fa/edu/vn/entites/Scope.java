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
@AllArgsConstructor
@NoArgsConstructor
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scope_id")
    private Integer scopeId;

    @Column(name = "scope")
    private String scope;
    @Column
    private String remarks;

    @OneToMany(mappedBy = "scope")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public Scope(String scope) {
        this.scope = scope;
    }
}
