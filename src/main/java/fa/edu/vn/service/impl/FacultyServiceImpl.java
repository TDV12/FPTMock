package fa.edu.vn.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.edu.vn.entites.Faculty;
import fa.edu.vn.repository.IFacultyRepository;
import fa.edu.vn.service.IFacultyService;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements IFacultyService {

    private final IFacultyRepository facultyRepository;

    @Override
    public List<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findFacultyByFacultyName(String name) {
        Optional<Faculty> faculty = facultyRepository.findFacultyByFacultyName(name);
        if (faculty.isPresent()) {
            return faculty.get();
        }
        return facultyRepository.save(new Faculty(name));
    }

}
