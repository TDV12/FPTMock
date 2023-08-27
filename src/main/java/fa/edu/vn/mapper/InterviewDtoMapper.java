package fa.edu.vn.mapper;

import org.mapstruct.Mapper;


import fa.edu.vn.dto.InterviewDto;

import fa.edu.vn.entites.Interview;

@Mapper(componentModel = "spring")
public interface InterviewDtoMapper extends EntitiesMapper<Interview, InterviewDto> {

}
