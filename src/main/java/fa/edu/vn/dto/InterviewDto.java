package fa.edu.vn.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDto {
	private String time;
	private String date;
	private String interviewer;
	private String comments;
	private String result;
}
