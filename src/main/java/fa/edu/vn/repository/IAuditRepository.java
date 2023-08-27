package fa.edu.vn.repository;

import fa.edu.vn.entites.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAuditRepository extends JpaRepository<Audit, Integer> {

    @Query(value = "SELECT c FROM Audit c WHERE c.classBatch.classId=?2 AND c.deleteFlag=?1")
    List<Audit> findAllAuditByDeleteFlagAndClassId(String status, Integer id);
}
