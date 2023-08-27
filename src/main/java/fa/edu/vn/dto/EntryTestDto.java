package fa.edu.vn.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryTestDto {
	private String time;
	private String date;
	private String languageValuator;
	private String languageResult;
	private String technicalValuator;
	private String technicalResult;
	private String result;

}
