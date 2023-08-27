package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.MasterTrainerDropDownDto;
import fa.edu.vn.entites.MasterTrainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMasterTrainerRepository extends JpaRepository<MasterTrainer, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.MasterTrainerDropDownDto(p.masterTrainerId,c.account) FROM " +
            "MasterTrainer p LEFT JOIN p.trainerProfile c")
    List<MasterTrainerDropDownDto> findAllMasterTrainerDropDownDto();
}
