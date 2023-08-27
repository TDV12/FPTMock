package fa.edu.vn.repository;

import fa.edu.vn.entites.ClassAdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminProfileRepository extends JpaRepository<ClassAdminProfile,Integer> {
}
