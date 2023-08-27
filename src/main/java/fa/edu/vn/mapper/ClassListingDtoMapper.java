package fa.edu.vn.mapper;


import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.entites.ClassBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClassListingDtoMapper {

    @Mappings({
            @Mapping(target = "className",source = "classBatch.className.className"),
            @Mapping(target = "location",source = "classBatch.location.locationName")
    })
    ClassListingDto toDto(ClassBatch classBatch);
}
