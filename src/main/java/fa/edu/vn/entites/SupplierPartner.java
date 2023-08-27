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
public class SupplierPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_partner_id")
    private Integer supplierPartnerId;

    @Column
    private String remarks;

    @Column(name = "supplier_partner_name")
    private String supplierPartnerName;

    @OneToMany(mappedBy = "supplierPartner", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ClassBatch> classBatch;

    public SupplierPartner(String supplierPartnerName) {
        this.supplierPartnerName = supplierPartnerName;
    }
}
