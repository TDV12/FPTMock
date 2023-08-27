package fa.edu.vn.controller.traineeManager;

import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.enums.ClassStatusEnum;
import fa.edu.vn.enums.TraineeStatusEnum;
import fa.edu.vn.mapper.TraineeListingDtoMapper;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.IMasterService;
import fa.edu.vn.service.ITraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AddTraineeInClassController {

    private final ITraineeService traineeService;

    private final IClassBatchService classBatchService;

    private final TraineeListingDtoMapper traineeListingDtoMapper;

    private final IMasterService masterService;


    @PostMapping("/addTraineeInClass/{classCode}")
    public String addTraineeInClass(@RequestBody List<Integer> empIds, @PathVariable String classCode, HttpServletResponse response, Model model) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        List<Trainee> trainees = traineeService.findAllByTraineeCandidateId(empIds);
        List<Trainee> collect = trainees.stream().
                filter(e -> TraineeStatusEnum.Enrolled.equals(e.getStatus()))
                .collect(Collectors.toList());
        if (empIds.isEmpty() || ObjectUtils.isEmpty(classCode)
                || Objects.isNull(classBatch)
                || !ClassStatusEnum.Draft.equals(classBatch.getStatus())
                || !collect.isEmpty()) {
            classBatchService.appendDataForViewMode(new ClassBatch(), model);
            model.addAttribute("trainees", traineeListingDtoMapper.toTraineeListingDtos(trainees));
            model.addAttribute("mess", "Trainee Status equal 'Enrolled' or Status Class not equal Draft");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "classInViewMode";
        }
        trainees.forEach(e -> {
            e.setClassBatch(classBatch);
            e.setStatus(TraineeStatusEnum.Enrolled);
        });
        List<Trainee> traineesAfterSave = traineeService.saveAllTrainee(trainees);
        classBatchService.appendDataForViewMode(classBatch, model);
        model.addAttribute("trainees", traineeListingDtoMapper.toTraineeListingDtos(traineesAfterSave));
        return "classInViewMode";
    }

}
