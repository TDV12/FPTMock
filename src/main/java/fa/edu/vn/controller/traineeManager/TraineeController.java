package fa.edu.vn.controller.traineeManager;

import fa.edu.vn.dto.TraineeSearchDto;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.service.ITraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TraineeController {

	@Autowired
	private ITraineeService traineeService;


    @GetMapping("/traineeListing")
	public String showTraineeListing(@RequestParam @Nullable Integer traineeCandidateId,
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

		Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "traineeCandidateId");
		Page<TraineeSearchDto> trainees;

		if ((traineeCandidateId == null || "".equals(traineeCandidateId))&&(account == null || "".equals(account))&&(fullName == null || "".equals(fullName))
				&&(dateOfBirth == null || "".equals(dateOfBirth))&&(phone == null || "".equals(phone))&&(email == null || "".equals(email))) {
			Page<Trainee> trainees2 = traineeService.findAll(pageable);
			model.addAttribute("trainees", trainees2.getContent());
			model.addAttribute("totalPage", trainees2.getTotalPages());
		}else {
			List<TraineeSearchDto> traineeList = traineeService.findTraineeSearch(traineeCandidateId, account, fullName, dateOfBirth, phone, email);
			int start = (int)pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), traineeList.size());

			trainees = new PageImpl<TraineeSearchDto>(traineeList.subList(start, end), pageable,traineeList.size());
			model.addAttribute("traineeSearchs", trainees);
			model.addAttribute("totalPage", trainees.getTotalPages());
			model.addAttribute("traineeCandidateId2", traineeCandidateId);
			model.addAttribute("account2", account);
			model.addAttribute("name2", fullName);
			model.addAttribute("DOB2", dateOfBirth);
			model.addAttribute("phone2", phone);
			model.addAttribute("email2", email);

		}
		model.addAttribute("currentPage", page);
		model.addAttribute("sizePage", size);
		return "traineeListing";
	}
    
    @GetMapping("/importTrainee")
    public String showImportTrainee(){
        return "importTrainee";
    }
}
