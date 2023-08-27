package fa.edu.vn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.edu.vn.entites.Faculty;

public interface IFacultyRepository extends JpaRepository<Faculty,Integer> {

	Faculty getByFacultyId(Integer facultyId);

	Optional<Faculty> findFacultyByFacultyName(String name);

	Optional<Faculty> findByFacultyName(String name);


}
