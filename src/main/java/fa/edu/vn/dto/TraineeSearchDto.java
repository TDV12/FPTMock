package fa.edu.vn.dto;

import java.time.LocalDate;

import fa.edu.vn.enums.TraineeStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraineeSearchDto {
	
	   private Integer traineeCandidateId;
	    private String account;
	    private String fullName;
	    private LocalDate dateOfBirth;
	    private String gender;
	    private String universityName;
	    private String facultyName;
	    private String phone;
	    private String email;
	    private TraineeStatusEnum status;

}
