package fa.edu.vn.service.impl;

import fa.edu.vn.entites.DetailLocation;
import fa.edu.vn.repository.IDetailLocationRepository;
import fa.edu.vn.service.IDetailLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailLocationServiceImpl implements IDetailLocationService {
    @Autowired
    private IDetailLocationRepository locationRepository;

    @Override
    public DetailLocation findDetailLocationById(Integer id) {
        return locationRepository.findDetailLocationById(id).orElseThrow(() -> new RuntimeException("Can't find Detail " +
                "Location"));
    }
}
