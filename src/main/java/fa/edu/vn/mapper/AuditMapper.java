package fa.edu.vn.mapper;

import fa.edu.vn.dto.AuditDto;
import fa.edu.vn.entites.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuditMapper extends EntitiesMapper<Audit, AuditDto> {

    @Override
    @Mappings({
            @Mapping(target = "eventDate", source = "eventDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "deadLine", source = "deadLine", dateFormat = "yyyy-MM-dd"),
    })
    Audit toEntity(AuditDto dto);

    @Override
    @Mappings({
            @Mapping(target = "eventDate", source = "eventDate", dateFormat = "yyyy-MM-dd"),
            @Mapping(target = "deadLine", source = "deadLine", dateFormat = "yyyy-MM-dd"),
    })
    List<Audit> toEntities(List<AuditDto> dtos);
}
