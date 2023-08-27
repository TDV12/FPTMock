package fa.edu.vn.dto;

import fa.edu.vn.enums.TraineeStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeListingDto {
    private Integer empId;
    private String account;
    private String name;
    private LocalDate dob;
    private String gender;
    private String university;
    private String faculty;
    private String phone;
    private String email;
    private TraineeStatusEnum status;

}
