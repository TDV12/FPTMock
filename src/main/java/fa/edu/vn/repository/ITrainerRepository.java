package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.TrainerDropDownDto;
import fa.edu.vn.entites.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITrainerRepository extends JpaRepository<Trainer, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.TrainerDropDownDto(p.trainerId,c.account) FROM Trainer p LEFT JOIN p.trainerProfile c")
    List<TrainerDropDownDto> findAllTrainerDropDownDto();
}
