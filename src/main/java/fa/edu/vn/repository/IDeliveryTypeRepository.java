package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.DeliveryTypeDropDownDto;
import fa.edu.vn.entites.DeliveryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDeliveryTypeRepository extends JpaRepository<DeliveryType, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.DeliveryTypeDropDownDto(p.deliveryTypeId,p.deliveryType) " +
            "FROM DeliveryType p")
    List<DeliveryTypeDropDownDto> findDeliveryDropDownDto();
}
