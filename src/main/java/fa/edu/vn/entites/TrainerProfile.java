package fa.edu.vn.entites;

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
public class TrainerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_profile_id")
    private Integer trainerProfileId;

    @Column(unique = true)
    private String account;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth", columnDefinition = "date")
    private LocalDate dateOfBirth;

    @Column
    private Integer gender;

    @Column
    private Integer unit;

    @Column
    private String major;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String email;

    @Column
    private String experience;

    @Column
    private String remarks;

    @OneToOne(mappedBy = "trainerProfile")
    @ToString.Exclude
    private Trainer trainer;

    @OneToOne(mappedBy = "trainerProfile")
    @ToString.Exclude
    private MasterTrainer masterTrainer;

    public TrainerProfile(String account, String fullName, LocalDate dateOfBirth, Integer gender, Integer unit, String major, String phone, String email, String experience, String remarks, Trainer trainer) {
        this.account = account;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.unit = unit;
        this.major = major;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
        this.remarks = remarks;
        this.trainer = trainer;
    }

    public TrainerProfile(String account, String fullName, LocalDate dateOfBirth, Integer gender, Integer unit, String major, String phone, String email, String experience, String remarks, MasterTrainer masterTrainer) {
        this.account = account;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.unit = unit;
        this.major = major;
        this.phone = phone;
        this.email = email;
        this.experience = experience;
        this.remarks = remarks;
        this.masterTrainer = masterTrainer;
    }
}
