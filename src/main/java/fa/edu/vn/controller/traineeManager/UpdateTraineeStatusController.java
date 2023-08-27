package fa.edu.vn.controller.traineeManager;

import fa.edu.vn.dto.TraineeStatusDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.enums.TraineeStatusEnum;
import fa.edu.vn.mapper.TraineeListingDtoMapper;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ITraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UpdateTraineeStatusController {

    private final ITraineeService traineeService;

    private final IClassBatchService classBatchService;

    private final TraineeListingDtoMapper traineeListingDtoMapper;


    @PostMapping("/traineeStatusChange/{classCode}")
    public String updateTraineeStatus(@PathVariable String classCode, @RequestBody List<TraineeStatusDto> traineeStatusDto, Model model) {
        Map<Integer, String> mapTraineeStatusDto = new HashMap<>();
        traineeStatusDto.forEach(e -> mapTraineeStatusDto.put(e.getId(), e.getStatus()));
        List<Integer> collect = traineeStatusDto.stream().map(TraineeStatusDto::getId).collect(Collectors.toList());
        List<Trainee> traineeDB = traineeService.findAllByTraineeCandidateId(collect);
        List<Trainee> traineeSave = traineeDB.stream().map(e -> {
            String status = mapTraineeStatusDto.get(e.getTraineeCandidateId());
            if (status != null) {
                e.setStatus(TraineeStatusEnum.valueOf(status));
            }
            return e;
        }).collect(Collectors.toList());
        List<Trainee> trainees = traineeService.saveAllTrainee(traineeSave);
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        classBatchService.appendDataForViewMode(classBatch, model);
        model.addAttribute("trainees", traineeListingDtoMapper.toTraineeListingDtos(trainees));
        return "classInViewMode";
    }
}
