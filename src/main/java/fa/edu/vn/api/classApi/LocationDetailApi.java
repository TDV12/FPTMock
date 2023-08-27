package fa.edu.vn.api.classApi;

import fa.edu.vn.dto.dropDownDto.DetailLocationDropDownDto;
import fa.edu.vn.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationDetailApi {
    @Autowired
    private ILocationService locationService;

    @GetMapping("/api/location/{value}")
    public List<DetailLocationDropDownDto> getLocation(@PathVariable Integer value) {
        if (value == null) {
            return null;
        }
        return locationService.findAllLocationDetailByLocationId(value);
    }

}
