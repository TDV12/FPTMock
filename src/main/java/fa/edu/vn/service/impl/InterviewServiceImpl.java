package fa.edu.vn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.Interview;
import fa.edu.vn.repository.IEntryTestRepository;
import fa.edu.vn.repository.IInterviewRepository;
import fa.edu.vn.service.IInterviewService;

@Service
public class InterviewServiceImpl implements IInterviewService {

	@Autowired
	private IInterviewRepository intervieweRepository;
	
	@Override
	public List<Interview> findByCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return intervieweRepository.findByCandidate(candidate);
	}

}
