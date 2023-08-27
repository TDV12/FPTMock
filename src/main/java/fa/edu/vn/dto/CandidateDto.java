package fa.edu.vn.dto;


import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class CandidateDto {

	private String type;
	private String status;
	private String applicationtDate;
	private Integer channelId;
	private Integer locationId;
//	private String account;
	private String fullName;
	private String gender;
	private String dateOfBirth;
	private MultipartFile CV;
	private Integer universityId;
	private String universityNameOther;
	private Integer facultyId;
	private String facultyNameOther;
	private String phone;
	private String email;
	private String skill;
	private String gradurationYear;
	private String foreignLanguage;
	private String level;
	private String note;
	
	
}
