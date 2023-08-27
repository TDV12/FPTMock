package fa.edu.vn.controller.classManager;

import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.dto.TraineeListingDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.mapper.ClassListingDtoMapper;
import fa.edu.vn.mapper.TraineeListingDtoMapper;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ITraineeService;
import fa.edu.vn.util.IAppendDataToPaging;
import fa.edu.vn.util.IConvertFunction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassViewController {

    private final IClassBatchService classBatchService;

    private final ITraineeService traineeService;

    private final ClassListingDtoMapper classListingDtoMapper;

    private final IConvertFunction<Trainee> convertFunction;

    private final TraineeListingDtoMapper traineeListingDtoMapper;

    private final IAppendDataToPaging iAppendDataToPaging;

    @GetMapping("/classListing")
    public String showClassListing(Model model) {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "classId");
        Page<ClassListingDto> classListingDtos = classBatchService.pageClass(pageable);
        classBatchService.appendDataPage(model, classListingDtos, 1, 2);
        return "classListings";
    }

    @GetMapping("/classListing/Search")
    public String showClassListingAfterSearch(@RequestParam @Nullable String currentPage,
                                              @RequestParam @Nullable String sizePage,
                                              @RequestParam @Nullable Integer location,
                                              @RequestParam @Nullable Integer className,
                                              @RequestParam @Nullable String status,
                                              @RequestParam @Nullable String fromDate,
                                              @RequestParam @Nullable String toDate,
                                              Model model) {
        if (currentPage == null || "NaN".equals(currentPage)) {
            currentPage = "1";
        }
        if (sizePage == null) {
            sizePage = "5";
        }
        int page = Integer.parseInt(currentPage);
        int size = Integer.parseInt(sizePage);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.ASC, "classId");
        if (ObjectUtils.isEmpty(location) && ObjectUtils.isEmpty(className)
                && ObjectUtils.isEmpty(status) && ObjectUtils.isEmpty(fromDate)
                && ObjectUtils.isEmpty(toDate)) {
            Page<ClassListingDto> classListingDtos = classBatchService.pageClass(pageable);
            classBatchService.appendDataPage(model, classListingDtos, page, size);
            return "classListingTable";
        }
        Page<ClassBatch> pageClassByCondition = classBatchService.findPageClassByCondition(location, className, status, fromDate, toDate, pageable);
        Page<ClassListingDto> classListingDtoPage = pageClassByCondition.map(classListingDtoMapper::toDto);
        classBatchService.appendDataPage(model, classListingDtoPage, page, size);
        return "classListingTable";
    }

    @GetMapping("/classInViewMode/{code}")
    public String viewModeUsingClassCode(@PathVariable("code") String code, Model model) {
        if (ObjectUtils.isEmpty(code)) {
            return "classInViewMode";
        }
        ClassBatch classBatch = classBatchService.findClassDetailByCode(code);
        List<Trainee> traineeListingDtoByClassId = traineeService.findTraineeByClassId(classBatch.getClassId());
        Page<Trainee> page = convertFunction.convertListToPage(PageRequest.of(0, 5), traineeListingDtoByClassId);
        model.addAttribute("trainees", page.map(traineeListingDtoMapper::toTraineeListingDto));
        model.addAttribute("totalPage2", page.getTotalPages());
        model.addAttribute("currentPage2", 1);
        model.addAttribute("sizePage2", 5);
        return classBatchService.appendDataForViewMode(classBatch, model);
    }

}
