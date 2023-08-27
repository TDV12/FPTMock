package fa.edu.vn.controller.traineeManager;

import fa.edu.vn.dto.FieldErrorsDto;
import fa.edu.vn.dto.ImportTraineeDto;
import fa.edu.vn.dto.TraineeListingDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.enums.ClassStatusEnum;
import fa.edu.vn.mapper.TraineeListingDtoMapper;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ITraineeService;
import fa.edu.vn.util.IAppendDataToPaging;
import fa.edu.vn.util.IConvertFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ImportFileTraineeController {

    private final ITraineeService traineeService;

    private final IClassBatchService classBatchService;

    private final TraineeListingDtoMapper traineeListingDtoMapper;

    private final IConvertFunction<Trainee> convertFunction;

    private final IAppendDataToPaging iAppendDataToPaging;

    private final String classInViewMode = "classInViewMode";

    private final String traineesPage = "trainees";

    private List<Trainee> trainees;

    @PostMapping("/importFileTrainee")
    public String importFileTrainee(@ModelAttribute("importTraineeDto") ImportTraineeDto importTraineeDto, Model model, HttpServletResponse response) {
        List<FieldErrorsDto> errorsField = traineeService.validateFieldInImportFile(importTraineeDto.getMultipartFile());
        ClassBatch classBatch = classBatchService.findClassDetailByCode(importTraineeDto.getClassCode());
        if (!ClassStatusEnum.Draft.equals(classBatch.getStatus())) {
            classBatchService.appendDataForViewMode(classBatch, model);
            model.addAttribute("mess", "Only import Student in Class has Status is 'Draft'");
            response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            return classInViewMode;
        }
        if (!errorsField.isEmpty()) {
            model.addAttribute("fieldErrors", errorsField);
            classBatchService.appendDataForViewMode(classBatch, model);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return classInViewMode;
        }
        trainees = traineeService.readFileAndSaveTrainee(importTraineeDto.getMultipartFile(), classBatch);
        classBatchService.appendDataForViewMode(classBatch, model);
        Pageable pageable = PageRequest.of(0, 2);
        Page<Trainee> pageTrainee = convertFunction.convertListToPage(pageable, trainees);
        Page<TraineeListingDto> pageTraineeListingDto = pageTrainee.map(traineeListingDtoMapper::toTraineeListingDto);
        model.addAttribute(traineesPage, pageTraineeListingDto);
        iAppendDataToPaging.appendDataToPaging(model, pageTraineeListingDto.getTotalPages(), 1, 2);
        return classInViewMode;
    }

    @GetMapping("/trainee/Search/{classCode}")
    public String searchTrainee(@PathVariable String classCode,
                                @RequestParam @Nullable String currentPage,
                                @RequestParam @Nullable String sizePage,
                                @RequestParam @Nullable String emplId,
                                @RequestParam @Nullable String emplAccount,
                                @RequestParam @Nullable String emplName,
                                @RequestParam @Nullable String emplDoB,
                                @RequestParam @Nullable String emplPhone,
                                @RequestParam @Nullable String emplEmail,
                                Model model) {
        if (currentPage == null || "NaN".equals(currentPage)) {
            currentPage = "1";
        }
        if (sizePage == null) {
            sizePage = "5";
        }
        int page = Integer.parseInt(currentPage);
        int size = Integer.parseInt(sizePage);
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        Pageable pageable = PageRequest.of(page - 1, size);

        if (!ObjectUtils.isEmpty(emplId) || !ObjectUtils.isEmpty(emplAccount)
                || !ObjectUtils.isEmpty(emplName) || !ObjectUtils.isEmpty(emplDoB)
                || !ObjectUtils.isEmpty(emplPhone) || !ObjectUtils.isEmpty(emplEmail) || !ObjectUtils.isEmpty(sizePage)) {

            Page<Trainee> pageTraineeByConditions = traineeService.findTraineeByMultiConditions(pageable, emplId, emplAccount, emplName, emplDoB, emplPhone, emplEmail, classBatch.getClassId());
            Page<TraineeListingDto> pageTraineeListingDto = pageTraineeByConditions.map(traineeListingDtoMapper::toTraineeListingDto);
            model.addAttribute(traineesPage, pageTraineeListingDto);
            model.addAttribute("totalPage2", pageTraineeListingDto.getTotalPages());
            model.addAttribute("currentPage2", page);
            model.addAttribute("sizePage2", size);

        } else if (!trainees.isEmpty()) {
            Page<Trainee> pageTrainee = convertFunction.convertListToPage(pageable, trainees);
            Page<TraineeListingDto> pageTraineeListingDto = pageTrainee.map(traineeListingDtoMapper::toTraineeListingDto);
            model.addAttribute(traineesPage, pageTraineeListingDto);
            model.addAttribute("totalPage2", pageTraineeListingDto.getTotalPages());
            model.addAttribute("currentPage2", page);
            model.addAttribute("sizePage2", size);
        }

        classBatchService.appendDataForViewMode(classBatch, model);
        return classInViewMode;
    }


}
