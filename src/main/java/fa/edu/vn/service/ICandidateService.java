package fa.edu.vn.service;

import java.security.Principal;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fa.edu.vn.dto.CandidateDto;
import fa.edu.vn.dto.CandidateResultDto;
import fa.edu.vn.dto.CandidateSearchDto;
import fa.edu.vn.entites.Candidate;


public interface ICandidateService {
	boolean save(CandidateDto candidateDto, Principal principal);
	
	Page<Candidate> findAll(Pageable pageable);
	
	List<Candidate> findAll();
	
	Candidate findByEmplId(Integer emplId);

	boolean saveAndUpdate(CandidateDto candidateDto, Integer emplId, Principal principal);

	boolean delete(Integer emplId);

	boolean deleteMutil(List<Integer> intListEmplId);

	boolean saveAndUpdateResult(CandidateResultDto candidateResultDto, Integer emplId,Principal principal);
	
	String checkPhone(CandidateDto candidateDto);
	
	String checkEmail(CandidateDto candidateDto);

	List<CandidateSearchDto> findCandidateSearch(Integer emplId, String account, String fullName, String dateOfBirth,
			String phone, String email );

	String checkUniversity(CandidateDto candidateDto);

	String checkFaculty(CandidateDto candidateDto);
	
	Resource getFileResource(String fileName);
	

}
