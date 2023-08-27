package fa.edu.vn.entites;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class AllowanceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "[group]")
    private Integer group;

    @Column
    private String remarks;

    @OneToOne
    @JoinColumn(name = "allowance")
    private Allowance allowance;

}
