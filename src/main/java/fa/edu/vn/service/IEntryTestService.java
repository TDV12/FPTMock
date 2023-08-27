package fa.edu.vn.service;

import java.util.List;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;

public interface IEntryTestService {

	List<EntryTest> findByCandidate(Candidate candidate);
}
