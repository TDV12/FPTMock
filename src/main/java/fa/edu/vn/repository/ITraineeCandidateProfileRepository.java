package fa.edu.vn.repository;

import fa.edu.vn.entites.TraineeCandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ITraineeCandidateProfileRepository extends JpaRepository<TraineeCandidateProfile, String> {

    Optional<TraineeCandidateProfile> findByPhone(String phone);

    Optional<TraineeCandidateProfile> findByEmail(String email);

    Optional<TraineeCandidateProfile> findByAccount(String account);


}
