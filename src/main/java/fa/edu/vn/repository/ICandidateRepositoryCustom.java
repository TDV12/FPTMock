package fa.edu.vn.repository;

import java.util.List;

import fa.edu.vn.dto.CandidateSearchDto;

public interface ICandidateRepositoryCustom {

	List<CandidateSearchDto> findCandidateSearch(Integer emplId, String account, String fullName, String dateOfBirth,
			String phone, String email);
}
