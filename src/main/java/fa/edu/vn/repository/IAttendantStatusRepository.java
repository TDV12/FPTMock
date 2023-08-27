package fa.edu.vn.repository;

import fa.edu.vn.entites.AttendantStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttendantStatusRepository extends JpaRepository<AttendantStatus,Integer> {
}
