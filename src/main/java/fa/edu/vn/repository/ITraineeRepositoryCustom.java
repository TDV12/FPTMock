package fa.edu.vn.repository;

import fa.edu.vn.dto.TraineeSearchDto;
import fa.edu.vn.entites.Trainee;

import java.util.List;
import java.util.Optional;

public interface ITraineeRepositoryCustom {

    Optional<Trainee> findTraineeByNameAndDobAndPhoneAndEmailCustom(String name, String dob, String phone, String email);
    
    List<TraineeSearchDto> findTraineeSearch(Integer traineeCandidateId, String account, String fullName,
			String dateOfBirth, String phone, String email);


}
