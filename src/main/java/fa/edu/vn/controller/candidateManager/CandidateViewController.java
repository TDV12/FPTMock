package fa.edu.vn.controller.candidateManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;
import fa.edu.vn.entites.Interview;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.service.ICandidateService;
import fa.edu.vn.service.IEntryTestService;
import fa.edu.vn.service.IInterviewService;

@Controller
public class CandidateViewController {

	@Value("${CV.location.url}")
	private String path;

	@Autowired
	private ICandidateService candidateService;

	@Autowired
	private IEntryTestService entrytestService;

	@Autowired
	private IInterviewService interviewService;

	@GetMapping("/viewCandidate/{id}")
	public String showViewCandidate(Model model, @PathVariable("id") Integer id) {
		Candidate candidate = candidateService.findByEmplId(id);
		TraineeCandidateProfile traineeCandidateProfile = candidate.getTraineeCandidateProfile();
		List<EntryTest> entryTests = entrytestService.findByCandidate(candidate);
		List<Interview> interviews = interviewService.findByCandidate(candidate);
		if (!entryTests.isEmpty()) {
			model.addAttribute("entryTests", entryTests);
		}
		if (!interviews.isEmpty()) {
			model.addAttribute("interviews", interviews);
		}
		if (traineeCandidateProfile.getCv() != null) {
			model.addAttribute("fileName", traineeCandidateProfile.getCv());
			model.addAttribute("fileNameUUID", traineeCandidateProfile.getCv());
		}

		model.addAttribute("candidate", candidate);
		return "/viewCandidate";
	}

}
