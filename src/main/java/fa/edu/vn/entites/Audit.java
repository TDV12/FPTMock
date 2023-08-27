package fa.edu.vn.entites;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Integer auditId;

    @Column(columnDefinition = "date")
    private LocalDate eventDate;

    @Column(name = "event_category")
    private String eventCategory;

    @Column(name = "related_party_people")
    private String relatedPartyPeople;

    @Column(name = "action")
    private String action;

    @Column
    private String pic;

    @Column
    private LocalDate deadLine;

    @Column
    private String note;

    @Column(name = "delete_flag")
    private String deleteFlag;

    @ManyToOne
    @JoinColumn(name = "class_batch_id")
    @ToString.Exclude
    private ClassBatch classBatch;

}
