package fa.edu.vn.repository;

import fa.edu.vn.dto.TraineeListingDto;
import fa.edu.vn.entites.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITraineeRepository extends JpaRepository<Trainee, Integer>, JpaSpecificationExecutor<Trainee> {

    @Query(value = "SELECT c FROM Trainee c LEFT JOIN c.traineeCandidateProfile p WHERE p.fullName =?1 AND p.dateOfBirth=?2 AND p.phone=?3 and p.email=?4")
    Optional<Trainee> findTraineeByTraineeName(String name, LocalDate dob, String phone, String email);

    @Query(value = "SELECT c FROM Trainee c WHERE c.traineeCandidateId IN ?1")
    List<Trainee> findAllByTraineeCandidateId(List<Integer> ids);

    @Query(value = "SELECT new fa.edu.vn.dto.TraineeListingDto(c.trainee.traineeCandidateId," +
            "c.account,c.fullName,c.dateOfBirth,c.gender,c.university.universityName," +
            "c.faculty.facultyName,c.phone,c.email,c.trainee.status) FROM TraineeCandidateProfile c")
    List<TraineeListingDto> findTraineeListingDto();


    List<Trainee> findTraineeByClassBatchClassId(Integer id);

	Trainee findByTraineeCandidateId(Integer id);

}
