package fa.edu.vn.repository;


import fa.edu.vn.entites.Candidate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICandidateRepository extends JpaRepository<Candidate,Integer> {
	
	List<Candidate> findAll();
	
	Page<Candidate> findAll(Pageable pageable);
	
	Candidate findByEmplId(Integer emplId);

	Optional<Candidate> findByAccount(String account);

	
}
