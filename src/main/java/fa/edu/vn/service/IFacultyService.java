package fa.edu.vn.service;

import java.util.List;

import fa.edu.vn.entites.Faculty;

public interface IFacultyService {

	List<Faculty> findAll();

	Faculty findFacultyByFacultyName(String name);

}
