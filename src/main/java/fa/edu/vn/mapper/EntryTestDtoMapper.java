package fa.edu.vn.mapper;


import org.mapstruct.Mapper;

import fa.edu.vn.dto.EntryTestDto;

import fa.edu.vn.entites.EntryTest;

@Mapper(componentModel = "spring")
public interface EntryTestDtoMapper extends EntitiesMapper<EntryTest, EntryTestDto> {
	
}
