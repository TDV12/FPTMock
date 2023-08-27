package fa.edu.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;
import fa.edu.vn.repository.ICandidateRepository;
import fa.edu.vn.repository.IEntryTestRepository;
import fa.edu.vn.service.IEntryTestService;




@Service
public class EntryTestServiceImpl implements IEntryTestService {
	@Autowired
	private IEntryTestRepository entryTesteRepository;
	
	@Override
	public List<EntryTest> findByCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return entryTesteRepository.findByCandidate(candidate);
	}

}
