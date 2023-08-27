package fa.edu.vn.service;

import fa.edu.vn.dto.FieldErrorsDto;
import fa.edu.vn.dto.TraineeListingDto;
import fa.edu.vn.dto.TraineeSearchDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.entites.Trainee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITraineeService {
    List<Trainee> saveAllTrainee(List<Trainee> trainees);

    List<Trainee> readFileAndSaveTrainee(MultipartFile file, ClassBatch classBatch);

    List<FieldErrorsDto> validateFieldInImportFile(MultipartFile multipartFile);

    List<Trainee> findAllByTraineeCandidateId(List<Integer> ids);

    List<Trainee> findTraineeByClassId(Integer id);

    Page<Trainee> findTraineeByMultiConditions(Pageable pageable, String id, String account, String name, String dob, String phone, String email, Integer idClass);

    Page<Trainee> findTraineeDbByMultiConditions(Pageable pageable, String id, String account, String name, String dob, String phone, String email, String status);

    Page<Trainee> findTraineeByStatus(Pageable pageable, String status);

	Trainee findByTraineeCandidateId(Integer id);

	Page<Trainee> findAll(Pageable pageable);

	List<TraineeSearchDto> findTraineeSearch(Integer traineeCandidateId, String account, String fullName,
			String dateOfBirth, String phone, String email);


}
