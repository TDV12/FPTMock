package fa.edu.vn.api.classApi;

import fa.edu.vn.service.IClassBatchService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ClazzCodeGeneratedApi {

    @Autowired
    private ILocationService locationService;

    @Autowired
    private IClassBatchService classBatchService;

    @Autowired
    private IMasterService masterService;

    @GetMapping("/clazzCodeGenerated")
    public StringBuilder generatedClazzCodeApi(@RequestParam @Nullable Integer locationId,
                                               @RequestParam @Nullable String exceptedStartDate,
                                               @RequestParam @Nullable Integer clazzNameId) {
        if (locationId == null || exceptedStartDate == null || clazzNameId == null) {
            return null;
        }
        StringBuilder classCode = new StringBuilder();
        if (locationId != null) {
            String[] locationName = locationService.findById(locationId).getLocationName().split(" ");
            for (String c : locationName) {
                classCode.append(c.charAt(0));
            }
        }
        classCode.append("_");
        String[] className = masterService.findClassNameById(clazzNameId).getClassName().split(" ");
        if ("Fresher".equals(className[0])) {
            classCode.append("FR_");
            classCode.append(className[className.length - 1]);
        } else if ("Campus".equals(className[0])) {
            classCode.append("CPL_");
            classCode.append(className[className.length - 1]);
        }
        if (exceptedStartDate != null) {
            classCode.append("_" + exceptedStartDate.substring(2, 4));
        }
        classCode.append("_" + classBatchService.findMaxClassBatchIdByClassNameId(clazzNameId));
        return classCode;
    }
}
