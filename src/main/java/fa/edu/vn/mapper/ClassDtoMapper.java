package fa.edu.vn.mapper;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.entites.Audit;
import fa.edu.vn.entites.ClassBatch;
import org.mapstruct.*;

import javax.persistence.ManyToOne;
import java.util.List;

@Mapper(componentModel = "spring", uses = {
        AuditMapper.class,
        BudgetMapper.class
})
public interface ClassDtoMapper {
    @Mappings({
            @Mapping(target = "className", source = "classBatch.className.classNameId"),
            @Mapping(target = "exceptedStartDate", source = "classBatch.exceptedStartDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "exceptedEndDate", source = "classBatch.exceptedEndDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "actualStartDate", source = "classBatch.actualStartDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "actualEndDate", source = "classBatch.actualEndDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "budgetCode", source = "classBatch.budget.budgetId"),
            @Mapping(target = "classAdmin", source = "classBatch.classAdmin.classAdminId"),
            @Mapping(target = "subjectType", source = "classBatch.subjectType.subjectTypeId"),
            @Mapping(target = "subSubjectType", source = "classBatch.subSubjectType.subSubjectTypeId"),
            @Mapping(target = "deliveryType", source = "classBatch.deliveryType.deliveryTypeId"),
            @Mapping(target = "formatType", source = "classBatch.formatType.formatTypeId"),
            @Mapping(target = "scope", source = "classBatch.scope.scopeId"),
            @Mapping(target = "masterTrainer", source = "classBatch.masterTrainer.masterTrainerId"),
            @Mapping(target = "trainer", source = "classBatch.trainer.trainerId"),
            @Mapping(target = "locationId", source = "classBatch.location.locationId"),
            @Mapping(target = "detailLocation", source = "classBatch.detailLocation.id"),
            @Mapping(target = "learningPath", ignore = true),
            @Mapping(target = "status", source = "classBatch.status"),
            @Mapping(target = "supplierPartner", source = "classBatch.supplierPartner.supplierPartnerId")
    })
    ClassDto toDto(ClassBatch classBatch);


}
