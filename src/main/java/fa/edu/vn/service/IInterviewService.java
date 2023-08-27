package fa.edu.vn.service;

import java.util.List;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.Interview;

public interface IInterviewService {

	List<Interview> findByCandidate(Candidate candidate);

}
