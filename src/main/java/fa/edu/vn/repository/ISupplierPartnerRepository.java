package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.SupplierPartnerDropDownDto;
import fa.edu.vn.entites.SupplierPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISupplierPartnerRepository extends JpaRepository<SupplierPartner, Integer> {
    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.SupplierPartnerDropDownDto(p.supplierPartnerId,p.supplierPartnerName) FROM " +
            "SupplierPartner p")
    List<SupplierPartnerDropDownDto> findAllSupplierPartnerDto();
}
