package fa.edu.vn.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateSearchDto {

	
    private Integer emplId;
    private String account;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String universityName;
    private String facultyName;
    private String phone;
    private String email;
    private String status;
}
