package fa.edu.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fa.edu.vn.entites.Candidate;

import fa.edu.vn.entites.Interview;

public interface IInterviewRepository extends JpaRepository<Interview,Integer>{

	List<Interview> findByCandidate(Candidate candidate);
//
//	@Query(value ="DELETE FROM Interview i WHERE i.candidate.emplId =:emplId")
//	void deleteAllByCandidateId(Integer emplId);
//	
//	void deleteInterviewByCandidate_emplId(Integer emplId);

}
