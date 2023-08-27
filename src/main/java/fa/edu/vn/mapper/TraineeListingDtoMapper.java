package fa.edu.vn.mapper;

import fa.edu.vn.dto.TraineeListingDto;
import fa.edu.vn.entites.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TraineeListingDtoMapper {

    @Mappings(
            {
                    @Mapping(target = "empId", source = "trainee.traineeCandidateId"),
                    @Mapping(target = "account", source = "trainee.traineeCandidateProfile.account"),
                    @Mapping(target = "name", source = "trainee.traineeCandidateProfile.fullName"),
                    @Mapping(target = "dob", source = "trainee.traineeCandidateProfile.dateOfBirth"),
                    @Mapping(target = "gender", source = "trainee.traineeCandidateProfile.gender"),
                    @Mapping(target = "university", source = "trainee.traineeCandidateProfile.university.universityName"),
                    @Mapping(target = "faculty", source = "trainee.traineeCandidateProfile.faculty.facultyName"),
                    @Mapping(target = "phone", source = "trainee.traineeCandidateProfile.phone"),
                    @Mapping(target = "email", source = "trainee.traineeCandidateProfile.email"),
                    @Mapping(target = "status", source = "trainee.status")
            }
    )
    TraineeListingDto toTraineeListingDto(Trainee trainee);

    List<TraineeListingDto> toTraineeListingDtos(List<Trainee> trainees);

}
