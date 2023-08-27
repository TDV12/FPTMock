package fa.edu.vn.controller.classManager;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.enums.ClassStatusEnum;
import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UpdateClassController {

    @Value("${learningPath.location.url}")
    private String path;

    private final IClassBatchService classBatchService;

    private final IMasterService masterService;

    private final ILocationService locationService;


    @PostMapping("/updateClass")
    public String updateClass(@Valid @ModelAttribute ClassDto classDto, BindingResult bindingResult, Model model,
                              HttpServletResponse response, Principal principal) {
        if (bindingResult.hasErrors()) {
            appendDataAndThrowMess(model, "Update Error", classDto, response);
            return "classInViewMode";
        }
        if (ClassStatusEnum.valueOf(classDto.getStatus()).equals(ClassStatusEnum.Draft)) {
            boolean result = classBatchService.save(classDto, principal) != null;
            if (!result) {
                appendDataAndThrowMess(model, "Update Error", classDto, response);
                return "classInViewMode";
            }
        } else {
            appendDataAndThrowMess(model, "Only can Update Class Status Draft", classDto, response);
            return "classInViewMode";
        }
        model.addAttribute("className", classBatchService.findAllClassNames());
        model.addAttribute("locationName", locationService.findLocationDropDownDtoName());
        model.addAttribute("classBaths", classBatchService.findClassListing());
        return "classListings";
    }

    @GetMapping(value = "/downloadFile/{fileName}")
    public void getFile(
            @PathVariable("fileName") String fileName,
            HttpServletResponse response) throws IOException {

        Resource resource = classBatchService.getFileResource(fileName);
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

    private void appendDataAndThrowMess(Model model, String mess, ClassDto classDto, HttpServletResponse response) {
        masterService.appendDataForDropDown(model, classDto);
        model.addAttribute("mess", mess);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

    }
}
