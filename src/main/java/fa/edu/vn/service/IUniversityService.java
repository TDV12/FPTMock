package fa.edu.vn.service;

import java.util.List;
import java.util.Optional;

import fa.edu.vn.entites.University;

public interface IUniversityService {

    List<University> findAll();

    University findByUniversityName(String name);

    University save(University university);
}
