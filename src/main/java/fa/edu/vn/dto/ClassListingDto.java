package fa.edu.vn.dto;

import fa.edu.vn.enums.ClassStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassListingDto {

    private Integer classId;
    private String classCode;
    private String className;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private String location;
    private ClassStatusEnum status;
}
