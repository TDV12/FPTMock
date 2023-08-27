package fa.edu.vn.repository;

import fa.edu.vn.dto.dropDownDto.ScopeDropDownDto;
import fa.edu.vn.entites.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IScopeRepository extends JpaRepository<Scope, Integer> {

    @Query(value = "SELECT new fa.edu.vn.dto.dropDownDto.ScopeDropDownDto(p.scopeId,p.scope) FROM Scope p")
    List<ScopeDropDownDto> findScopeDropDownDtop();
}
