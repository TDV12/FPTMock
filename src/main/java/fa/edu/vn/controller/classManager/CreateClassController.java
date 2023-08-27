package fa.edu.vn.controller.classManager;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.service.IClassAdminService;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CreateClassController {

    @Autowired
    private IClassBatchService classBatchService;

    @Autowired
    private IMasterService masterService;

    @GetMapping("/createNewClass")
    public String showCreateNewClass() {
        return "createClass";
    }

    @PostMapping("/createNewClass")
    public String createClass(@Valid @ModelAttribute("classDto") ClassDto classDto, BindingResult bindingResult,
                              Model model,
                              HttpServletResponse response, Principal principal) {
        if (bindingResult.hasErrors()) {
            masterService.appendDataForDropDown(model, classDto);
            model.addAttribute("mess", "Save Error");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "classInformation";
        }
        ClassBatch classBatch = classBatchService.save(classDto, principal);
        if (classBatch == null) {
            masterService.appendDataForDropDown(model, classDto);
            model.addAttribute("mess", "Save Error");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "classInformation";
        }
        model.addAttribute("mess", "Class Code is " + classBatch.getClassCode());
        return "createClass";
    }


}

