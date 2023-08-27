package fa.edu.vn.repository;

import fa.edu.vn.entites.BudgetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBugdetDetailRepository extends JpaRepository<BudgetDetail, Integer> {

    @Query(value = "SELECT c FROM BudgetDetail c WHERE c.classBatch.classId =?2 AND c.deleteFlag = ?1")
    List<BudgetDetail> findAllBudgetDetailByStatusAndClassId(String status, Integer id);
}

