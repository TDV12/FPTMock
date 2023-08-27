package fa.edu.vn.controller.classManager;

import fa.edu.vn.dto.ImportTraineeDto;
import fa.edu.vn.dto.UpdateRemarksDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.enums.ClassStatusEnum;
import fa.edu.vn.mapper.ClassDtoMapper;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IMasterService;
import fa.edu.vn.service.ITraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ClassStatusController {

    private final IClassBatchService classBatchService;

    private final ILocationService locationService;

    private final ClassDtoMapper classDtoMapper;

    private final IMasterService masterService;

    private final ITraineeService traineeService;

    @GetMapping("/cancelClass/{classId}")
    public String cancelClass(@PathVariable Integer classId, Model model, HttpServletResponse response) {
        ClassBatch classBatchById = classBatchService.findClassBatchById(classId);
        if (classBatchById.getStatus().equals(ClassStatusEnum.Draft)) {
            classBatchById.setStatus(ClassStatusEnum.Cancel);
            classBatchService.save(classBatchById);
            appendData(model);
        } else {
            appendData(model);
            model.addAttribute("mess", "Only 'Cancel' Class Status 'Draft' ");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "classListings";
    }

    @GetMapping("/submitClass/{classCode}")
    public String submitClass(@PathVariable String classCode, Model model, HttpServletResponse response, Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.Draft,
                ClassStatusEnum.Submitted,
                "Submitted By", null);
        return viewReturn(result, model, response, "Only 'Submit' Class Status is 'Draft'", classBatch);
    }

    @GetMapping("/approveClass/{classCode}")
    public String approveClass(@PathVariable String classCode, Model model, HttpServletResponse response, Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.Submitted,
                ClassStatusEnum.Planning,
                "Approved By", null);
        return viewReturn(result, model, response, "Only 'Approve' Class Status is 'Submitted'", classBatch);
    }

    @PostMapping("/rejectClass")
    public String rejectClass(@ModelAttribute UpdateRemarksDto updateRemarksDto, Model model, HttpServletResponse response, Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(updateRemarksDto.getClassCode());
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.Submitted,
                ClassStatusEnum.Reject,
                "Rejected By", updateRemarksDto);
        return viewReturn(result, model, response, "Only 'Reject' Class Status is 'Submitted'", classBatch);
    }

    @GetMapping("/acceptClass/{classCode}")
    public String acceptClass(@PathVariable String classCode, Model model,
                              HttpServletResponse response, Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.Planning,
                ClassStatusEnum.Planned,
                "Approved By", null);
        return viewReturn(result, model, response, "Only 'Accept' Class Status is 'Planning'", classBatch);
    }

    @PostMapping("/declineClass")
    private String declineClass(@ModelAttribute UpdateRemarksDto updateRemarksDto, Model model, HttpServletResponse response,
                                Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(updateRemarksDto.getClassCode());
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.Planning,
                ClassStatusEnum.Declined,
                "Declined By", updateRemarksDto);
        return viewReturn(result, model, response, "Only 'Decline' Class Status is 'Planning'", classBatch);
    }

    @GetMapping("/startClass/{classCode}")
    public String startClass(@PathVariable String classCode, Model model,
                             HttpServletResponse response, Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.Planned,
                ClassStatusEnum.InProcess,
                "Approved By", null);
        return viewReturn(result, model, response, "Only 'Accept' Class Status is 'Planning'", classBatch);
    }

    @GetMapping("/finishClass/{classCode}")
    public String finishClass(@PathVariable String classCode, Model model,
                              HttpServletResponse response, Principal principal) {
        ClassBatch classBatch = classBatchService.findClassDetailByCode(classCode);
        boolean result = checkStatusClass(classBatch, principal,
                ClassStatusEnum.InProcess,
                ClassStatusEnum.PendingForReview,
                "Finish By", null);
        return viewReturn(result, model, response, "Only 'Finished' Class Status is 'In-Progress'", classBatch);
    }


    private boolean checkStatusClass(ClassBatch classBatch, Principal principal,
                                     ClassStatusEnum classStatusCondition, ClassStatusEnum classStatusEnum,
                                     String eventChange, UpdateRemarksDto updateRemarksDto) {
        if (classBatch.getStatus().equals(classStatusCondition)) {
            classBatch.setStatus(classStatusEnum);
            classBatch.setHistory(classBatch.getHistory()
                    .concat("<<" + LocalDateTime.now() + ">>-\"" +
                            eventChange + "\" <<" + principal.getName() + ">>"));
            if (updateRemarksDto != null) {
                classBatch.setHistory(classBatch.getHistory().concat("-<<" + updateRemarksDto.getRemarks() + ">>"));
            }
            classBatchService.save(classBatch);
            return true;
        }
        return false;
    }

    private String viewReturn(boolean condition, Model model, HttpServletResponse response, String mess, ClassBatch classBatch) {
        if (condition) {
            appendData(model);
            return "classListings";
        }
        masterService.appendDataForDropDown(model, classDtoMapper.toDto(classBatch));
        model.addAttribute("mess", mess);
        model.addAttribute("importTraineeDto", new ImportTraineeDto());
        model.addAttribute("trainees", traineeService.findTraineeByClassId(classBatch.getClassId()));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return "classInViewMode";
    }

    private void appendData(Model model) {
        model.addAttribute("className", classBatchService.findAllClassNames());
        model.addAttribute("locationName", locationService.findLocationDropDownDtoName());
        model.addAttribute("classBaths", classBatchService.findClassListing());
    }
}
