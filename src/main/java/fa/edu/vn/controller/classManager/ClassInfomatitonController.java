package fa.edu.vn.controller.classManager;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.service.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassInfomatitonController {

    @Autowired
    private IMasterService masterService;

    @GetMapping("/classInformation")
    public String showClassInformation(Model model) {
        masterService.appendDataForDropDown(model, new ClassDto());
        return "classInformation";
    }
}
