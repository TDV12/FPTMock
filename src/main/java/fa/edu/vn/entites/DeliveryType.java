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
public class DeliveryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_type_id")
    private Integer deliveryTypeId;

    @Column(name = "delivery_type_name")
    private String deliveryType;

    @Column
    private String remarks;

    @OneToMany(mappedBy = "deliveryType")
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public DeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
