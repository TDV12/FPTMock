package fa.edu.vn.controller.traineeManager;

import fa.edu.vn.dto.TraineeListingDto;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.mapper.TraineeListingDtoMapper;
import fa.edu.vn.service.ITraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AddTraineeController {

    private final ITraineeService traineeService;

    private final TraineeListingDtoMapper traineeListingDtoMapper;

    @GetMapping("/addTrainee")
    public String showFormAddTrainee(@RequestParam @Nullable String currentPage,
                                     @RequestParam @Nullable String sizePage,
                                     @RequestParam @Nullable String emplId,
                                     @RequestParam @Nullable String emplAccount,
                                     @RequestParam @Nullable String emplName,
                                     @RequestParam @Nullable String emplDoB,
                                     @RequestParam @Nullable String emplPhone,
                                     @RequestParam @Nullable String emplEmail
            , Model model) {
        if (ObjectUtils.isEmpty(currentPage) || "NaN".equals(currentPage)) {
            currentPage = "1";
        }
        if (ObjectUtils.isEmpty(sizePage)) {
            sizePage = "5";
        }
        int page = Integer.parseInt(currentPage);
        int size = Integer.parseInt(sizePage);
        Pageable pageable = PageRequest.of(page - 1, size);
        if (!ObjectUtils.isEmpty(emplId) || !ObjectUtils.isEmpty(emplAccount)
                || !ObjectUtils.isEmpty(emplName) || !ObjectUtils.isEmpty(emplDoB)
                || !ObjectUtils.isEmpty(emplPhone) || !ObjectUtils.isEmpty(emplEmail)) {
            Page<Trainee> pageTraineeByConditions = traineeService.findTraineeDbByMultiConditions(pageable, emplId, emplAccount, emplName, emplDoB, emplPhone, emplEmail, "Enrolled");
            Page<TraineeListingDto> pageTraineeListingDto = pageTraineeByConditions.map(traineeListingDtoMapper::toTraineeListingDto);
            model.addAttribute("traineesDB", pageTraineeListingDto);
            model.addAttribute("totalPage1", pageTraineeListingDto.getTotalPages());
            model.addAttribute("currentPage1", currentPage);
            model.addAttribute("sizePage1", size);
        } else {
            Page<Trainee> enrolled = traineeService.findTraineeByStatus(pageable, "Enrolled");
            model.addAttribute("traineesDB", enrolled.map(traineeListingDtoMapper::toTraineeListingDto));
            model.addAttribute("totalPage1", enrolled.getTotalPages());
            model.addAttribute("currentPage1", currentPage);
            model.addAttribute("sizePage1", size);
        }
        return "addTrainee";
    }
}
