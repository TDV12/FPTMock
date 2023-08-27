package fa.edu.vn.repository;

import fa.edu.vn.entites.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUniversityRepository extends JpaRepository<University, Integer> {

    University getByUniversityId(Integer universityId);

    @Query(value = "SELECT c FROM University c WHERE c.universityName=?1")
    Optional<University> findByUniversityName(String name);
}
