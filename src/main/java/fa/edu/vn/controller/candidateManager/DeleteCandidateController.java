package fa.edu.vn.controller.candidateManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fa.edu.vn.entites.Candidate;
import fa.edu.vn.service.ICandidateService;

@Controller
public class DeleteCandidateController {

	@Autowired
	private ICandidateService candidateService;

	@PostMapping("/deleteOneCandidate")
	public String deleteCandidate(@ModelAttribute("emplId") String emplId, Model model, HttpServletResponse response) {
		System.out.println(emplId);
		boolean result = candidateService.delete(Integer.parseInt(emplId));
		if (!result) {
			model.addAttribute("saveError", "Save Error");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "candidateInformation";
		}
		List<Candidate> candidates = candidateService.findAll();
		model.addAttribute("candidates", candidates);

		return "/candidateListings";
	}

	@PostMapping("/deleteMutilCandidate")
	public String deleteMutilCandidate(@ModelAttribute("emplId") List<String> emplIds, Model model,
			HttpServletResponse response) {
		System.out.println(emplIds);
		List<Integer> intListEmplId = new ArrayList<>();
		intListEmplId.addAll(emplIds.stream().map(Integer::valueOf).collect(Collectors.toList()));
		boolean result = candidateService.deleteMutil(intListEmplId);
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
