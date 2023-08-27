package fa.edu.vn.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import fa.edu.vn.dto.FieldErrorsDto;
import fa.edu.vn.dto.TraineeSearchDto;
import fa.edu.vn.entites.ClassBatch;
import fa.edu.vn.entites.Faculty;
import fa.edu.vn.entites.Trainee;
import fa.edu.vn.entites.TraineeCandidateProfile;
import fa.edu.vn.entites.University;
import fa.edu.vn.enums.AllocationStatusEnum;
import fa.edu.vn.enums.TraineeStatusEnum;
import fa.edu.vn.repository.ITraineeRepository;
import fa.edu.vn.repository.ITraineeRepositoryCustom;
import fa.edu.vn.service.IFacultyService;
import fa.edu.vn.service.ITraineeService;
import fa.edu.vn.service.IUniversityService;
import fa.edu.vn.util.Regex;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements ITraineeService {

    private final ITraineeRepository traineeRepository;

    private final IUniversityService iUniversityService;

    private final IFacultyService facultyService;

    private final ITraineeRepositoryCustom traineeRepositoryCustom;

    private final String CANDIDATE_PROFILE = "traineeCandidateProfile";

    @Override
    public List<Trainee> saveAllTrainee(List<Trainee> trainees) {
        return traineeRepository.saveAll(trainees);
    }

    @Override
    public List<Trainee> readFileAndSaveTrainee(MultipartFile file, ClassBatch classBatch) {
        List<Trainee> traineesSaveList = new ArrayList<>();
        List<Trainee> traineesDB = new ArrayList<>();
        boolean isFirstRow = true;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                String name = row.getCell(3).getStringCellValue();
                String dob = row.getCell(8).getStringCellValue();
                String email = row.getCell(10).getStringCellValue();
                String phone = row.getCell(11).getStringCellValue();
                Trainee traineeDB = findTraineeByNameAndDobAndPhoneAndEmail(name, dob, phone, email);
                if (traineeDB != null) {
                    traineesDB.add(traineeDB);
                    if (rowIterator.hasNext()) {
                        continue;
                    }
                    break;
                }
                Trainee trainee = new Trainee();
                TraineeCandidateProfile traineeCandidateProfile = new TraineeCandidateProfile();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 2:
                            if (checkDataCellString(cell)) {
                                traineeCandidateProfile.setAccount(cell.getStringCellValue());
                                break;
                            }
                            break;
                        case 3:
                            if (checkDataCellString(cell)) {
                                traineeCandidateProfile.setFullName(cell.getStringCellValue());
                                break;
                            }
                            break;
                        case 6:
                            if (checkDataCellString(cell)) {
                                University university = checkUniversity(cell.getStringCellValue());
                                traineeCandidateProfile.setUniversity(university);
                                break;
                            }
                            break;
                        case 7:
                            if (checkDataCellString(cell)) {
                                Faculty faculty = checkFaculty(cell.getStringCellValue());
                                traineeCandidateProfile.setFaculty(faculty);
                                break;
                            }
                            break;
                        case 8:
                            if (checkDataCellString(cell)) {
                                traineeCandidateProfile.setDateOfBirth(LocalDate.parse(cell.getStringCellValue()));
                                break;
                            }
                            break;
                        case 9:
                            if (checkDataCellString(cell) && isGenderValid(cell.getStringCellValue())) {
                                traineeCandidateProfile.setGender(cell.getStringCellValue().toLowerCase());
                                break;
                            }
                            break;
                        case 10:
                            if (checkDataCellString(cell) && isEmailValid(cell.getStringCellValue())) {
                                traineeCandidateProfile.setEmail(cell.getStringCellValue());
                                break;
                            }
                            break;
                        case 11:
                            if (checkDataCellString(cell)) {
                                traineeCandidateProfile.setPhone(cell.getStringCellValue());
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
                traineeCandidateProfile.setAllocationStatus(AllocationStatusEnum.NotAllocated);
                trainee.setTraineeCandidateProfile(traineeCandidateProfile);
                trainee.setStatus(TraineeStatusEnum.Enrolled);
                trainee.setClassBatch(classBatch);
                traineesSaveList.add(trainee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Trainee> trainees = saveAllTrainee(traineesSaveList);
        trainees.addAll(traineesDB);
        return trainees;
    }

    @Override
    public List<FieldErrorsDto> validateFieldInImportFile(MultipartFile multipartFile) {
        boolean isFirstRow = true;
        List<FieldErrorsDto> errors = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            while (rows.hasNext()) {
                Row row = rows.next();
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }
                Iterator<Cell> cells = row.cellIterator();
                FieldErrorsDto error = new FieldErrorsDto();
                StringBuilder messError = new StringBuilder();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) {
                        case 2:
                            if (!checkDataCellString(cell)) {
                                messError.append("Account is null in row " + row.getRowNum() + " ");
                                break;
                            }
                            break;
                        case 10:
                            if (!isEmailValid(cell.getStringCellValue())) {
                                messError.append("Email invalid in row " + row.getRowNum() + " ");
                                break;
                            }
                            break;
                        case 11:
                            if (!isValidPhone(cell.getStringCellValue())) {
                                messError.append("Phone invalid in row " + row.getRowNum());
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
                error.setError(messError);
                errors.add(error);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errors.stream().filter(e -> !ObjectUtils.isEmpty(e.getError())).collect(Collectors.toList());
    }

    @Override
    public List<Trainee> findAllByTraineeCandidateId(List<Integer> ids) {
        return traineeRepository.findAllByTraineeCandidateId(ids);
    }

    @Override
    public List<Trainee> findTraineeByClassId(Integer id) {
        return traineeRepository.findTraineeByClassBatchClassId(id);
    }

    @Override
    public Page<Trainee> findTraineeByMultiConditions(Pageable pageable, String id, String account, String name, String dob, String phone, String email, Integer idClass) {
        Specification<Trainee> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.equal(root.get("classBatch"), idClass));
            return queryMutilConditions(root, query, criteriaBuilder, conditions, id, account, name, dob, phone, email);
        };
        return traineeRepository.findAll(spec, pageable);
    }

    @Override
    public Page<Trainee> findTraineeDbByMultiConditions(Pageable pageable, String id, String account, String name, String dob, String phone, String email, String status) {
        Specification<Trainee> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> conditions = new ArrayList<>();
            conditions.add(criteriaBuilder.notEqual(root.get("status"), TraineeStatusEnum.valueOf(status)));
            return queryMutilConditions(root, query, criteriaBuilder, conditions, id, account, name, dob, phone, email);
        };
        return traineeRepository.findAll(spec, pageable);
    }

    @Override
    public Page<Trainee> findTraineeByStatus(Pageable pageable, String status) {
        Specification<Trainee> specification = (root, query, criteriaBuilder) ->
                query.where(criteriaBuilder.notEqual(root.get("status"), TraineeStatusEnum.valueOf(status))).getRestriction();
        return traineeRepository.findAll(specification, pageable);
    }

    private boolean checkDataCellString(Cell cell) {
        return !ObjectUtils.isEmpty(cell.getStringCellValue());
    }

    private Trainee findTraineeByNameAndDobAndPhoneAndEmail(String name, String dob, String phone, String email) {
        Optional<Trainee> trainee = traineeRepositoryCustom.findTraineeByNameAndDobAndPhoneAndEmailCustom(name, dob, phone, email);
        if (trainee.isPresent()) {
            return trainee.get();
        }
        return null;
    }

    private University checkUniversity(String universityName) {
        return iUniversityService.findByUniversityName(universityName);
    }

    private Faculty checkFaculty(String faculty) {
        return facultyService.findFacultyByFacultyName(faculty);
    }

    private boolean isGenderValid(String string) {
        return "male".equalsIgnoreCase(string) || "female".equalsIgnoreCase(string);
    }

    private boolean isEmailValid(String email) {
        return Pattern.compile(Regex.REGEX_EMAIL_FORMAT).matcher(email).find();
    }

    private boolean isValidPhone(String phone) {
        return Pattern.compile(Regex.REGEX_PHONE_FORMAT).matcher(phone).find();
    }

    private Predicate queryMutilConditions(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder, List<Predicate> conditions, String id, String account, String name, String dob, String phone,
                                           String email) {
        if (!ObjectUtils.isEmpty(id)) {
            conditions.add(criteriaBuilder.equal(root.get("traineeCandidateId"), Integer.parseInt(id)));
        } else {
            if (!ObjectUtils.isEmpty(account)) {
                conditions.add(criteriaBuilder.like(root.get(CANDIDATE_PROFILE).get("account"), "%" + account + "%"));
            }
            if (!ObjectUtils.isEmpty(name)) {
                conditions.add(criteriaBuilder.like(root.get(CANDIDATE_PROFILE).get("fullName"), "%" + name + "%"));
            }
            if (!ObjectUtils.isEmpty(dob)) {
                conditions.add(criteriaBuilder.equal(root.get(CANDIDATE_PROFILE).get("dateOfBirth"), LocalDate.parse(dob)));
            }
            if (!ObjectUtils.isEmpty(phone)) {
                conditions.add(criteriaBuilder.like(root.get(CANDIDATE_PROFILE).get("phone"), "%" + phone + "%"));
            }
            if (!ObjectUtils.isEmpty(email)) {
                conditions.add(criteriaBuilder.like(root.get(CANDIDATE_PROFILE).get("email"), "%" + email + "%"));
            }
        }
        return query.where(conditions.toArray(new Predicate[conditions.size()])).getRestriction();
    }


	@Override
	public Trainee findByTraineeCandidateId(Integer id) {
		return traineeRepository.findByTraineeCandidateId(id);
	}

	@Override
	public Page<Trainee> findAll(Pageable pageable) {
		return traineeRepository.findAll(pageable);
	}

	
	@Override
	public List<TraineeSearchDto> findTraineeSearch(Integer traineeCandidateId, String account, String fullName,
			String dateOfBirth, String phone, String email) {
		
		return traineeRepositoryCustom.findTraineeSearch(traineeCandidateId, account, fullName, dateOfBirth, phone, email);
	}

}
