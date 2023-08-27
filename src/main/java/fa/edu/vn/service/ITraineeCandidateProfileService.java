package fa.edu.vn.service;

import fa.edu.vn.entites.TraineeCandidateProfile;

public interface ITraineeCandidateProfileService {

    boolean isPhoneUnique(String phone);

    boolean isEmailUnique(String name);

    boolean isAccountUnique(String account);

    TraineeCandidateProfile save (TraineeCandidateProfile traineeCandidateProfile);

}
