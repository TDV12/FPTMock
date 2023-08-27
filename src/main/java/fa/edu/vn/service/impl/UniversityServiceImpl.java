package fa.edu.vn.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import fa.edu.vn.entites.University;
import fa.edu.vn.repository.IUniversityRepository;
import fa.edu.vn.service.IUniversityService;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements IUniversityService {

    private final IUniversityRepository universityRepository;

    @Override
    public List<University> findAll() {
        return universityRepository.findAll();
    }

    @Override
    public University findByUniversityName(String name) {
        Optional<University> university = universityRepository.findByUniversityName(name);
        if (university.isPresent()) {
            return university.get();
        }
        return save(new University(name));
    }

    @Override
    public University save(University university) {
        return universityRepository.save(university);
    }

}
