package fa.edu.vn.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fa.edu.vn.dto.CandidateDto;
import fa.edu.vn.dto.CandidateResultDto;
import fa.edu.vn.dto.CandidateSearchDto;
import fa.edu.vn.entites.Candidate;
import fa.edu.vn.entites.Channel;
import fa.edu.vn.entites.EntryTest;
import fa.edu.vn.entites.Faculty;
import fa.edu.vn.entites.Interview;
import fa.edu.vn.entites.Location;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.entites.University;
import fa.edu.vn.mapper.EntryTestDtoMapper;
import fa.edu.vn.mapper.InterviewDtoMapper;
import fa.edu.vn.repository.ICandidateRepository;
import fa.edu.vn.repository.ICandidateRepositoryCustom;
import fa.edu.vn.repository.IChannelRepository;
import fa.edu.vn.repository.IEntryTestRepository;
import fa.edu.vn.repository.IFacultyRepository;
import fa.edu.vn.repository.IInterviewRepository;
import fa.edu.vn.repository.ILocationRepository;
import fa.edu.vn.repository.ITraineeCandidateProfileRepository;
import fa.edu.vn.repository.IUniversityRepository;
import fa.edu.vn.service.ICandidateService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements ICandidateService {

	@Autowired
	private ICandidateRepository candidateRepository;
	
	@Autowired
	private ICandidateRepositoryCustom candidateRepositoryCustom;

	@Autowired
	private IChannelRepository channelRepository;

	private final Environment environment;

	@Autowired
	private ILocationRepository locationRepository;

	@Autowired
	private IUniversityRepository universityRepository;

	@Autowired
	private IFacultyRepository facultyRepository;

	@Autowired
	private IEntryTestRepository entryTestRepository;

	@Autowired
	private IInterviewRepository interviewRepository;

	@Autowired
	private EntryTestDtoMapper entryTestDtoMapper;

	@Autowired
	private InterviewDtoMapper interviewDtoMapper;

	@Autowired
	private ITraineeCandidateProfileRepository traineeCandidateProfileRepository;

	@Override
	public boolean save(CandidateDto candidateDto, Principal principal) {
		Candidate candidate = new Candidate();
		BeanUtils.copyProperties(candidateDto, candidate);
		candidate.setApplicationDate(LocalDate.parse(candidateDto.getApplicationtDate()));
		TraineeCandidateProfile traineeCandidateProfile = (TraineeCandidateProfile) copyProperties(candidateDto,
				new TraineeCandidateProfile());
		if (candidateDto.getUniversityId()==0) {
			University university = new University();
			String checkUni = checkUniversity(candidateDto);
			if (checkUni == null) {
				university.setUniversityName(candidateDto.getUniversityNameOther());
				traineeCandidateProfile.setUniversity(universityRepository.save(university));
			}else {
				university = universityRepository.findByUniversityName(candidateDto.getUniversityNameOther()).get();
				traineeCandidateProfile.setUniversity(university);
			}
			
		}else {
			University university = universityRepository.getByUniversityId(candidateDto.getUniversityId());
			traineeCandidateProfile.setUniversity(university);
		}
		
		if (candidateDto.getFacultyId()==0) {
			Faculty faculty = new Faculty();
			String checkFac = checkFaculty(candidateDto);
			if (checkFac == null) {
				faculty.setFacultyName(candidateDto.getFacultyNameOther());
				traineeCandidateProfile.setFaculty(facultyRepository.save(faculty));
			}else {
				faculty = facultyRepository.findByFacultyName(candidateDto.getFacultyNameOther()).get();
				traineeCandidateProfile.setFaculty(faculty);
			}
			
		}else {
			Faculty faculty = facultyRepository.getByFacultyId(candidateDto.getFacultyId());
			traineeCandidateProfile.setFaculty(faculty);
		}
		
		
		Channel channel = channelRepository.getByChannelId(candidateDto.getChannelId());
		Location location = locationRepository.getByLocationId(candidateDto.getLocationId());
		String CV = "";
		if (candidateDto.getCV() != null) {
			try {
				CV = saveFile(candidateDto);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			traineeCandidateProfile.setCv(CV);
		}
		traineeCandidateProfile.setDateOfBirth(LocalDate.parse(candidateDto.getDateOfBirth()));
		traineeCandidateProfile.setGraduationYear(LocalDate.parse(candidateDto.getGradurationYear()));
		traineeCandidateProfile.setAccount(checkAcount(candidateDto));
		candidate.setTraineeCandidateProfile(traineeCandidateProfile);
		candidate.setChannel(channel);
		candidate.setLocation(location);
		candidate.setHistory("<<"+ LocalDateTime.now() + ">>-\"Create By\" <<" + principal.getName() + ">>");
		candidateRepository.save(candidate);
		return true;
	}


	private Object copyProperties(CandidateDto candidateDto, Object object) {
		BeanUtils.copyProperties(candidateDto, object);
		return object;
	}

	@Override
	public Page<Candidate> findAll(Pageable pageable) {

		return candidateRepository.findAll(pageable);
	}
	
	@Override
	public List<Candidate> findAll() {

		return candidateRepository.findAll();
	}

	@Override
	public Candidate findByEmplId(Integer emplId) {

		return candidateRepository.findByEmplId(emplId);
	}

	@Override
	public boolean saveAndUpdate(CandidateDto candidateDto, Integer emplId,  Principal principal) {
		Candidate candidate = candidateRepository.findByEmplId(emplId);
		BeanUtils.copyProperties(candidateDto, candidate);
		candidate.setApplicationDate(LocalDate.parse(candidateDto.getApplicationtDate()));
		TraineeCandidateProfile traineeCandidateProfileCheckName = candidate.getTraineeCandidateProfile();
		String fullNameOld = traineeCandidateProfileCheckName.getFullName();
		String accountOld = traineeCandidateProfileCheckName.getAccount();
		TraineeCandidateProfile traineeCandidateProfile = (TraineeCandidateProfile) copyProperties(candidateDto,
				candidate.getTraineeCandidateProfile());
		if (candidateDto.getUniversityId()==0) {
			University university = new University();
			String checkUni = checkUniversity(candidateDto);
			if (checkUni == null) {
				university.setUniversityName(candidateDto.getUniversityNameOther());
				traineeCandidateProfile.setUniversity(universityRepository.save(university));
			}else {
				university = universityRepository.findByUniversityName(candidateDto.getUniversityNameOther()).get();
				traineeCandidateProfile.setUniversity(university);
			}
			
		}else {
			University university = universityRepository.getByUniversityId(candidateDto.getUniversityId());
			traineeCandidateProfile.setUniversity(university);
		}
		
		if (candidateDto.getFacultyId()==0) {
			Faculty faculty = new Faculty();
			String checkFac = checkFaculty(candidateDto);
			if (checkFac == null) {
				faculty.setFacultyName(candidateDto.getFacultyNameOther());
				traineeCandidateProfile.setFaculty(facultyRepository.save(faculty));
			}else {
				faculty = facultyRepository.findByFacultyName(candidateDto.getFacultyNameOther()).get();
				traineeCandidateProfile.setFaculty(faculty);
			}
			
		}else {
			Faculty faculty = facultyRepository.getByFacultyId(candidateDto.getFacultyId());
			traineeCandidateProfile.setFaculty(faculty);
		}
		
		
		Channel channel = channelRepository.getByChannelId(candidateDto.getChannelId());
		Location location = locationRepository.getByLocationId(candidateDto.getLocationId());
		
		String CV = "";
		if (candidateDto.getCV() != null) {
			try {
				CV = saveFile(candidateDto);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			traineeCandidateProfile.setCv(CV);
		}

	
		traineeCandidateProfile.setDateOfBirth(LocalDate.parse(candidateDto.getDateOfBirth()));
		traineeCandidateProfile.setGraduationYear(LocalDate.parse(candidateDto.getGradurationYear()));
		if ((fullNameOld.trim().replaceAll("\\s+", " ")).equals((traineeCandidateProfile.getFullName()).trim().replaceAll("\\s+", " "))) {
			traineeCandidateProfile.setAccount(accountOld);
			
		}else {
			traineeCandidateProfile.setAccount(checkAcount(candidateDto));
		
			
		}
		
		candidate.setTraineeCandidateProfile(traineeCandidateProfile);
		candidate.setChannel(channel);
		candidate.setLocation(location);
		candidate.setHistory("<<"+ LocalDateTime.now() + ">>-\"Update By\" <<" + principal.getName() + ">>");
		candidateRepository.save(candidate);
		return true;
	}
	
	private String saveFile(CandidateDto candidateDto) throws IOException {
		MultipartFile CV = candidateDto.getCV();
		if (CV == null || CV.getOriginalFilename() == null) {
			throw new IOException("Can't Find learning path file");
		}
		String property = environment.getProperty("CV.location.url");
		Path path = Paths.get(property);
		if (Files.notExists(path)) {
			Files.createDirectories(path);
		}
		int indexOfExtension = CV.getOriginalFilename().lastIndexOf(".");
		
		String fileFormat = CV.getOriginalFilename().substring(indexOfExtension);
		
		String fileName = UUID.randomUUID().toString() + fileFormat;
		
		String uploadPath = path + "/" + fileName;
		Files.write(Paths.get(uploadPath), CV.getBytes());
		return fileName;
	}

	@Override
	public boolean delete(Integer emplId) {
		candidateRepository.deleteById(emplId);
		return true;
	}

	@Override
	public boolean deleteMutil(List<Integer> intListEmplId) {

		candidateRepository.deleteAllById(intListEmplId);
		return true;
	}

	@Override
	public boolean saveAndUpdateResult(CandidateResultDto candidateResultDto, Integer emplId,Principal principal) {
		Candidate candidate = candidateRepository.findByEmplId(emplId);
		List<EntryTest> oldEntryTests = candidate.getEntryTests();
		List<Interview> oldInterviews = candidate.getInterviews();
		entryTestRepository.deleteAllInBatch(oldEntryTests);
		interviewRepository.deleteAllInBatch(oldInterviews);
		List<EntryTest> entryTests = entryTestDtoMapper.toEntities(candidateResultDto.getEntryTests());
		List<Interview> interviews = interviewDtoMapper.toEntities(candidateResultDto.getInterviews());

		candidate.setEntryTests(entryTests);
		candidate.setInterviews(interviews);
		if (entryTests != null) {
			entryTests.forEach(e -> e.setCandidate(candidate));
			candidate.setHistory("<<"+ LocalDateTime.now() + ">>-\"Test Update By\" <<" + principal.getName() + ">>");
		}

		if (interviews != null) {
			interviews.forEach(e -> e.setCandidate(candidate));
			candidate.setHistory("<<"+ LocalDateTime.now() + ">>-\"Interview Update By\" <<" + principal.getName() + ">>");
		}
	
	
		candidateRepository.save(candidate);
		return true;
	}

	private String checkAcount(CandidateDto candidateDto) {
		String st = candidateDto.getFullName();
		st=st.trim().toLowerCase();
        st = st.replaceAll("\\s+", " ");
        String[] temp= st.split(" ");
        
        st=String.valueOf(temp[temp.length-1].charAt(0)).toUpperCase()+ temp[temp.length-1].substring(1);
        for(int i=0;i<temp.length-1;i++) {
            st+=String.valueOf(temp[i].charAt(0)).toUpperCase();
            
        }
       
        int index = 0;
        String account = st;
  do {
	  st = account;
      st = st + index;
      index++;
      
} while (traineeCandidateProfileRepository.findByAccount(st).isPresent());
		return st;
	}

	@Override
	public String checkPhone(CandidateDto candidateDto) {
		Optional<TraineeCandidateProfile> traineeCandidateProfile = traineeCandidateProfileRepository
				.findByPhone(candidateDto.getPhone());
		if (traineeCandidateProfile.isPresent()) {
			return "PHONE IS EXITS";
		}
		return null;
	}

	@Override
	public String checkEmail(CandidateDto candidateDto) {
		Optional<TraineeCandidateProfile> traineeCandidateProfile2 = traineeCandidateProfileRepository
				.findByEmail(candidateDto.getEmail());
		if (traineeCandidateProfile2.isPresent()) {
			return "EMAIL IS EXITS";
		}
		return null;
	}
	
	@Override
	public String checkUniversity(CandidateDto candidateDto) {
		Optional<University> university = universityRepository.findByUniversityName(candidateDto.getUniversityNameOther());
		if (university.isPresent()) {
			return "University IS EXITS";
		}
		return null;
	}
	
	@Override
	public String checkFaculty(CandidateDto candidateDto) {
		Optional<Faculty> faculty = facultyRepository.findByFacultyName(candidateDto.getFacultyNameOther());
		if (faculty.isPresent()) {
			return "Faculty IS EXITS";
		}
		return null;
	}
	

	@Override
    public Resource getFileResource(String fileName) {
        Path path = Paths.get(environment.getProperty("CV.location.url")).resolve(fileName);
        try {
            Resource resource = new UrlResource(path.toUri());
            return resource;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

	@SuppressWarnings("unused")
	@Override
	public List<CandidateSearchDto> findCandidateSearch(Integer emplId, String account, String fullName, String dateOfBirth,
			String phone, String email) {
		return candidateRepositoryCustom.findCandidateSearch(emplId, account, fullName, dateOfBirth, phone, email);
	}

}
