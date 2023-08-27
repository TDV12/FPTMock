package fa.edu.vn.controller.traineeManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;
import fa.edu.vn.entites.Interview;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.service.IEntryTestService;
import fa.edu.vn.service.IInterviewService;
import fa.edu.vn.service.ITraineeService;


@Controller
public class ViewTraineeController {

	@Autowired
	private ITraineeService traineeService;
	
	@Autowired
	private IEntryTestService entrytestService;
	
	@Autowired
	private IInterviewService interviewService;
	
	
	@GetMapping("/viewTrainee/{id}")
	public String showViewCandidate(Model model,@PathVariable("id") Integer id) {
		Trainee trainee = traineeService.findByTraineeCandidateId(id);
		TraineeCandidateProfile traineeCandidateProfile = trainee.getTraineeCandidateProfile();
		
		 if (traineeCandidateProfile.getCv() != null) {
	            model.addAttribute("fileName", traineeCandidateProfile.getCv());
	            model.addAttribute("fileNameUUID", traineeCandidateProfile.getCv());
	        }
		
		
		model.addAttribute("trainee", trainee);
		return "/viewTrainee";
	}
}
