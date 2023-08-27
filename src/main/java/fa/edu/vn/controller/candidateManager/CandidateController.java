package fa.edu.vn.controller.candidateManager;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.edu.vn.dto.CandidateDto;
import fa.edu.vn.dto.CandidateResultDto;
import fa.edu.vn.dto.CandidateSearchDto;
import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.EntryTest;
import fa.edu.vn.entites.Interview;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.service.ICandidateService;
import fa.edu.vn.service.IChannelService;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.IEntryTestService;
import fa.edu.vn.service.IFacultyService;
import fa.edu.vn.service.IInterviewService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IUniversityService;

@Controller
public class CandidateController {
	
	@Value("${CV.location.url}")
    private String path;
	
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

	@GetMapping("/createNewCandidate")
	public String showCreateNewCandidate() {
		return "createCandidate";
	}
	
	@GetMapping("/resultCandidate")
	public String showResultCandidate() {
		return "resultCandidate";
	}

	@GetMapping("/candidateListing")
	public String showClassListing(@RequestParam @Nullable Integer emplId,
            @RequestParam @Nullable String account,
            @RequestParam @Nullable String fullName,
            @RequestParam @Nullable String dateOfBirth,
            @RequestParam @Nullable String phone,
            @RequestParam @Nullable String email,Model model, @RequestParam @Nullable String currentPage,
			@RequestParam @Nullable String sizePage) {
		if (currentPage == null) {
            currentPage = "1";
        }

        if (sizePage == null) {
            sizePage = "5";
        }

        int page = Integer.parseInt(currentPage);
        int size = Integer.parseInt(sizePage);
        
		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "emplId");
		Page<CandidateSearchDto> candidates;
		
		if ((emplId == null || "".equals(emplId))&&(account == null || "".equals(account))&&(fullName == null || "".equals(fullName))
				&&(dateOfBirth == null || "".equals(dateOfBirth))&&(phone == null || "".equals(phone))&&(email == null || "".equals(email))) {
			Page<Candidate> candidates2 = candidateService.findAll(pageable);
			model.addAttribute("candidates", candidates2.getContent());
			model.addAttribute("totalPage", candidates2.getTotalPages());
		}else {
			List<CandidateSearchDto> candidateList = candidateService.findCandidateSearch(emplId, account, fullName, dateOfBirth, phone, email);
			int start = (int)pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), candidateList.size());
			  
			candidates = new PageImpl<CandidateSearchDto>(candidateList.subList(start, end), pageable,candidateList.size());
			model.addAttribute("candidateSearchs", candidates);
			model.addAttribute("totalPage", candidates.getTotalPages());
			model.addAttribute("emplId2", emplId);
			model.addAttribute("account2", account);
			model.addAttribute("name2", fullName);
			model.addAttribute("DOB2", dateOfBirth);
			model.addAttribute("phone2", phone);
			model.addAttribute("email2", email);
			
		} 
		
		model.addAttribute("currentPage", page);
		
		model.addAttribute("sizePage", size);
	
		return "candidateListings";
	}

	@GetMapping("/candidateInformation")
	public String showCandidateInformation(Model model) {
		model.addAttribute("channels",channelService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("universitys",universityService.findAll());
		model.addAttribute("facultys",facultyService.findAll());
		model.addAttribute("candidateDto",new CandidateDto());
		
		return "candidateInformation";
	}
	
	 @PostMapping("/createNewCandidate")
	    public String createClass(@ModelAttribute("candidateDto") CandidateDto candidateDto, Model model, HttpServletResponse response,Principal principal){
		
		 	String messageP = candidateService.checkPhone(candidateDto);
		 	String messageE = candidateService.checkEmail(candidateDto);
		
			if (messageP != null) {
		 		model.addAttribute("messageP", messageP);
		 		model.addAttribute("channels",channelService.findAll());
				model.addAttribute("locations", locationService.findAll());
				model.addAttribute("universitys",universityService.findAll());
				model.addAttribute("facultys",facultyService.findAll());
				model.addAttribute("candidateDto", candidateDto);
				return "candidateInformation";
			}
			if (messageE != null) {
		 		model.addAttribute("messageE", messageE);
		 		model.addAttribute("channels",channelService.findAll());
				model.addAttribute("locations", locationService.findAll());
				model.addAttribute("universitys",universityService.findAll());
				model.addAttribute("facultys",facultyService.findAll());
				model.addAttribute("candidateDto", candidateDto);
				return "candidateInformation";
			}
	        boolean result = candidateService.save(candidateDto,principal);
	        System.out.println(candidateDto);
	     
	        if(!result){
	            model.addAttribute("saveError","Save Error");
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            return "candidateInformation";
	        }
	        List<Candidate> candidates = candidateService.findAll();
			model.addAttribute("candidates", candidates);
	        return "/candidateListings";
	    }

	 @GetMapping(value = "/downloadFileCandidate/{fileName}")
	    public void getFileCandidate(
	            @PathVariable("fileName") String fileName,
	            HttpServletResponse response) throws IOException {
	        Resource resource = candidateService.getFileResource(fileName);
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename =" + resource.getFilename());
	        ServletOutputStream outputStream = response.getOutputStream();
	        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(resource.getFile()));
	        byte[] buffer = new byte[8192];
	        int bytesRead = -1;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	        inputStream.close();
	        outputStream.close();
	    }
	
}
