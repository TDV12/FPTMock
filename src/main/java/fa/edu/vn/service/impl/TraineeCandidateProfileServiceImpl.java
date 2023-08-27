package fa.edu.vn.service.impl;

import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.repository.ITraineeCandidateProfileRepository;
import fa.edu.vn.service.ITraineeCandidateProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeCandidateProfileServiceImpl implements ITraineeCandidateProfileService {

    private final ITraineeCandidateProfileRepository traineeCandidateProfileRepository;

    @Override
    public boolean isPhoneUnique(String phone) {
        return traineeCandidateProfileRepository.findByPhone(phone).isPresent();
    }

    @Override
    public boolean isEmailUnique(String email) {
        return traineeCandidateProfileRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean isAccountUnique(String account) {
        return traineeCandidateProfileRepository.findByAccount(account).isPresent();
    }

    @Override
    public TraineeCandidateProfile save(TraineeCandidateProfile traineeCandidateProfile) {
        return traineeCandidateProfileRepository.save(traineeCandidateProfile);
    }
}
