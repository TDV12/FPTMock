package fa.edu.vn.repository;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface IEntryTestRepository extends JpaRepository<EntryTest,Integer> {
	
	List<EntryTest> findByCandidate(Candidate candidate);
	
//
//	@Query(value ="DELETE FROM EntryTest e WHERE e.candidate.emplId =:emplId")
//	void deleteAllByCandidateId(Integer emplId);
//	
//	void deleteEntryTestByCandidate_emplId(Integer emplId);
	

}
