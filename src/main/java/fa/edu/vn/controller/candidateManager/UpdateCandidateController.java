package fa.edu.vn.controller.candidateManager;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fa.edu.vn.dto.CandidateDto;
import fa.edu.vn.dto.CandidateResultDto;
import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;
import fa.edu.vn.entites.Interview;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.service.ICandidateService;
import fa.edu.vn.service.IChannelService;
import fa.edu.vn.service.IEntryTestService;
import fa.edu.vn.service.IFacultyService;
import fa.edu.vn.service.IInterviewService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IUniversityService;

@Controller
public class UpdateCandidateController {

	@Autowired
	private ICandidateService candidateService;

	@Autowired
	private ILocationService locationService;

	@Autowired
	private IChannelService channelService;

	@Autowired
	private IFacultyService facultyService;

	@Autowired
	private IUniversityService universityService;

	@Autowired
	private IEntryTestService entrytestService;

	@Autowired
	private IInterviewService interviewService;

	@GetMapping("/updateCandidate/{id}")
	public String showUpdateCandidate(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("channels", channelService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("universitys", universityService.findAll());
		model.addAttribute("facultys", facultyService.findAll());
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
		model.addAttribute("candidateDto", new CandidateDto());

		return "/updateCandidate";
	}

	@PostMapping("/updateOneCandidate")
	public String updateCandidate(@ModelAttribute("candidateDto") CandidateDto candidateDto,
			@ModelAttribute("emplId") Integer emplId, Model model, HttpServletResponse response, Principal principal) {
		System.out.println(candidateDto);
		System.out.println(emplId);
		Candidate candidate = candidateService.findByEmplId(emplId);
		TraineeCandidateProfile traineeCandidateProfile = candidate.getTraineeCandidateProfile();

		String messageP = candidateService.checkPhone(candidateDto);
		String messageE = candidateService.checkEmail(candidateDto);

		if (!candidateDto.getPhone().equals(traineeCandidateProfile.getPhone())) {
			if (messageP != null) {
				model.addAttribute("messageP", messageP);
				model.addAttribute("channels", channelService.findAll());
				model.addAttribute("locations", locationService.findAll());
				model.addAttribute("universitys", universityService.findAll());
				model.addAttribute("facultys", facultyService.findAll());
				model.addAttribute("candidateDto", candidateDto);
				model.addAttribute("candidate", candidate);
				System.out.println("2");
				return "updateCandidate";
			}
		}
		if (!candidateDto.getEmail().equals(traineeCandidateProfile.getEmail())) {
			if (messageE != null) {
				model.addAttribute("messageE", messageE);
				model.addAttribute("channels", channelService.findAll());
				model.addAttribute("locations", locationService.findAll());
				model.addAttribute("universitys", universityService.findAll());
				model.addAttribute("facultys", facultyService.findAll());
				model.addAttribute("candidateDto", candidateDto);
				model.addAttribute("candidate", candidate);
				System.out.println("3");
				return "updateCandidate";
			}
		}

		boolean result = candidateService.saveAndUpdate(candidateDto, emplId, principal);

		if (!result) {
			model.addAttribute("saveError", "Save Error");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "candidateInformation";
		}
		List<Candidate> candidates = candidateService.findAll();
		model.addAttribute("candidates", candidates);
		return "/candidateListings";
	}

	@PostMapping("/updateResultCandidate")
	public String updateCandidate(@ModelAttribute("candidateResultDto") CandidateResultDto candidateResultDto,
			@ModelAttribute("emplId") Integer emplId, Model model, HttpServletResponse response, Principal principal) {

		System.out.println(candidateResultDto);
		System.out.println(emplId);
		boolean result = candidateService.saveAndUpdateResult(candidateResultDto, emplId, principal);
		if (!result) {
			model.addAttribute("saveError", "Save Error");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "CandidateInformation";
		}
		List<Candidate> candidates = candidateService.findAll();
		model.addAttribute("candidates", candidates);
		return "/candidateListings";
	}

}
