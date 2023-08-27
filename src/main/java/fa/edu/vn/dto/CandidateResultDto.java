package fa.edu.vn.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResultDto {
	
	@Nullable
	private List<EntryTestDto> entryTests;
	
	@Nullable
	private List<InterviewDto> interviews;
	
	
	

}
