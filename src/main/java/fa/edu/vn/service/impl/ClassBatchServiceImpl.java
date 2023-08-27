package fa.edu.vn.service.impl;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.dto.ClassListingDto;
import fa.edu.vn.dto.ImportTraineeDto;
import fa.edu.vn.dto.dropDownDto.ClassNameDropDownDto;
import fa.edu.vn.entites.*;
import fa.edu.vn.enums.ClassStatusEnum;
import fa.edu.vn.mapper.AuditMapper;
import fa.edu.vn.mapper.BudgetMapper;
import fa.edu.vn.mapper.ClassDtoMapper;
import fa.edu.vn.repository.IClassRepository;
import fa.edu.vn.service.*;
import fa.edu.vn.util.IAppendDataToPaging;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassBatchServiceImpl implements IClassBatchService {

    private String deleted = "Deleted";

    private String active = "Active";

    private final Environment environment;

    private final IClassRepository classRepository;

    private final IDetailLocationService detailLocationService;

    private final ILocationService locationService;

    private final IClassAdminService classAdminService;

    private final AuditMapper auditMapper;

    private final BudgetMapper budgetMapper;

    private final IBudgetService budgetService;

    private final IAuditService auditService;

    private final IMasterService masterService;

    private final IBudgetDetailService budgetDetailService;

    private final ClassDtoMapper classDtoMapper;

    private final IAppendDataToPaging appendDataToPaging;

    @Override
    public ClassBatch save(ClassDto classDto, Principal principal) {
        if (!Objects.nonNull(classDto)) {
            return null;
        }
        ClassBatch classBatch = findClassDetailByCode(classDto.getClassCode());

        boolean createFlag = false;
        if (!Objects.nonNull(classBatch)) {
            classBatch = new ClassBatch();
            createFlag = true;
            classBatch.setStatus(ClassStatusEnum.Draft);
        }
        BeanUtils.copyProperties(classDto, classBatch, "history");

        if (createFlag) {
            classBatch.setHistory("<<" + LocalDateTime.now() + ">>-\"Create By\" <<" + principal.getName() + ">>");
        } else {
            classBatch.setHistory(classBatch.getHistory().concat("<<" + LocalDateTime.now() + ">>-\"Update By\" <<" + principal.getName() + ">>"));
        }

        classBatch.setClassName(masterService.findClassNameById(classDto.getClassName()));

        classBatch.setExceptedStartDate(LocalDate.parse(classDto.getExceptedStartDate()));
        classBatch.setExceptedEndDate(LocalDate.parse(classDto.getExceptedEndDate()));

        if (!checkSpace(classDto.getActualEndDate()) && !checkSpace(classDto.getActualStartDate())) {
            classBatch.setActualStartDate(LocalDate.parse(classDto.getActualStartDate()));
            classBatch.setActualEndDate(LocalDate.parse(classDto.getActualEndDate()));
        }

        Budget budget = budgetService.findBudgetById(classDto.getBudgetCode());
        classBatch.setBudget(budget);

        setBudgetDetail(classDto, classBatch);

        setAudit(classDto, classBatch, createFlag);

        Location location = locationService.findById(classDto.getLocationId());
        classBatch.setLocation(location);

        DetailLocation detailByLocation = detailLocationService.findDetailLocationById(classDto.getDetailLocation());
        classBatch.setDetailLocation(detailByLocation);

        ClassAdmin byAdminName = classAdminService.findClassAdminById(classDto.getClassAdmin());
        classBatch.setClassAdmin(byAdminName);

        SubjectType subjectType = masterService.findSubjectTypeById(classDto.getSubjectType());
        if (subjectType != null) {
            classBatch.setSubjectType(subjectType);
        }

        DeliveryType deliveryType = masterService.findDeliveryTypeById(classDto.getDeliveryType());
        if (deliveryType != null) {
            classBatch.setDeliveryType(deliveryType);
        }

        SubSubjectType subSubjectType = masterService.findSubSubjectTypeById(classDto.getSubSubjectType());
        if (subSubjectType != null) {
            classBatch.setSubSubjectType(subSubjectType);
        }

        FormatType formatType = masterService.findFormatTypeById(classDto.getFormatType());
        if (formatType != null) {
            classBatch.setFormatType(formatType);
        }

        Scope scope = masterService.findScopeById(classDto.getFormatType());
        if (scope != null) {
            classBatch.setScope(scope);
        }

        SupplierPartner supplierPartner = masterService.findSupplierPartnerById(classDto.getSupplierPartner());
        if (!checkSpace(supplierPartner.getSupplierPartnerName())) {
            classBatch.setSupplierPartner(supplierPartner);
        }

        MasterTrainer masterTrainer = masterService.findMasterTrainerById(classDto.getMasterTrainer());
        classBatch.setMasterTrainer(masterTrainer);

        Trainer trainer = masterService.findTrainerById(classDto.getTrainer());
        classBatch.setTrainer(trainer);

        String learningPathName = "";
        if (classDto.getLearningPath() != null) {
            try {
                learningPathName = saveFile(classDto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LearningPath learningPath = new LearningPath(learningPathName,
                    classDto.getLearningPath().getOriginalFilename());
            classBatch.setLearningPath(learningPath);
        }

        return save(classBatch);
    }

    @Override
    public ClassBatch save(ClassBatch classBatch) {
        return classRepository.save(classBatch);
    }

    @Override
    public List<ClassNameDropDownDto> findAllClassNames() {
        return classRepository.findAllClassName();
    }

    @Override
    public List<ClassListingDto> findClassListing() {
        return classRepository.findClassList();
    }

    @Override
    public ClassBatch findClassDetailByCode(String code) {
        ClassBatch detailClassBathByCode = classRepository.findClassBatchByClassCode(code);
        return detailClassBathByCode;
    }

    @Override
    public Integer findMaxClassBatchIdByClassNameId(Integer id) {
        Integer maxIdByClassNameId = classRepository.findMaxIdByClassNameId(id);
        if (maxIdByClassNameId == null) {
            return 1;
        }
        return maxIdByClassNameId + 1;
    }

    @Override
    public ClassBatch findClassBatchById(Integer id) {
        return classRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find Class Batch"));
    }

    @Override
    public Resource getFileResource(String fileName) {
        Path path = Paths.get(environment.getProperty("learningPath.location.url")).resolve(fileName);
        try {
            Resource resource = new UrlResource(path.toUri());
            return resource;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String appendDataForViewMode(ClassBatch classBatch, Model model) {
        ClassDto classDto = classDtoMapper.toDto(classBatch);
        classDto.setBudgets(budgetDetailService.mapAllBudgetDtoByStatusAndClassId(active, classBatch.getClassId()));
        classDto.setAudits(auditService.mappAllAuditByDeleteFlagAndClassId(active, classBatch.getClassId()));
        masterService.appendDataForDropDown(model, classDto);
        model.addAttribute("importTraineeDto", new ImportTraineeDto());
        if (classBatch.getLearningPath() != null) {
            model.addAttribute("fileName", classBatch.getLearningPath().getLearningPathOriginalName());
            model.addAttribute("fileNameUUID", classBatch.getLearningPath().getLearningPathName());
        }
        model.addAttribute("classDto", classDto);
        return "classInViewMode";
    }

    @Override
    public Page<ClassListingDto> pageClass(Pageable pageAble) {
        return classRepository.findClassListPage(pageAble);
    }

    @Override
    public Page<ClassBatch> findPageClassByCondition(Integer location, Integer className, String status, String fromDate, String toDate, Pageable pageable) {
        Specification<ClassBatch> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> conditions = new ArrayList<>();
            if (Objects.nonNull(location)) {
                conditions.add(criteriaBuilder.equal(root.get("location"), location));
            }
            if (Objects.nonNull(className)) {
                conditions.add(criteriaBuilder.equal(root.get("className"), className));
            }
            if (!ObjectUtils.isEmpty(status)) {
                conditions.add(criteriaBuilder.equal(root.get("status"), ClassStatusEnum.valueOf(status)));
            }
            if (!ObjectUtils.isEmpty(fromDate) && !ObjectUtils.isEmpty(toDate)) {
                conditions.add(criteriaBuilder.and(
                        criteriaBuilder.greaterThan(root.get("actualStartDate"), LocalDate.parse(fromDate)),
                        criteriaBuilder.lessThan(root.get("actualEndDate"), LocalDate.parse(toDate))
                ));
            } else {
                if (!ObjectUtils.isEmpty(fromDate)) {
                    conditions.add(criteriaBuilder.greaterThan(root.get("actualStartDate"), LocalDate.parse(fromDate)));
                }
                if (!ObjectUtils.isEmpty(toDate)) {
                    conditions.add(criteriaBuilder.lessThan(root.get("actualEndDate"), LocalDate.parse(toDate)));
                }
            }
            return query.where(conditions.toArray(new Predicate[conditions.size()])).getRestriction();
        };
        return classRepository.findAll(specification, pageable);
    }

    @Override
    public void appendDataPage(Model model, Page<ClassListingDto> classListingDtoPage, int page, int size) {
        model.addAttribute("className", findAllClassNames());
        model.addAttribute("locationName", locationService.findLocationDropDownDtoName());
        appendDataToPaging.appendDataToPaging(model, classListingDtoPage.getTotalPages(), page, size);
        model.addAttribute("classBaths", classListingDtoPage.getContent());
    }

    private boolean checkSpace(String value) {
        return ObjectUtils.isEmpty(value);
    }

    private String saveFile(ClassDto classDto) throws IOException {
        MultipartFile learningPath = classDto.getLearningPath();
        if (learningPath == null || learningPath.getOriginalFilename() == null) {
            throw new IOException("Can't Find learning path file");
        }
        String property = environment.getProperty("learningPath.location.url");
        Path path = Paths.get(property);
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
        int indexOfExtension = learningPath.getOriginalFilename().lastIndexOf(".");
        String fileFormat = learningPath.getOriginalFilename().substring(indexOfExtension);
        String fileName = UUID.randomUUID().toString() + fileFormat;
        String uploadPath = path + "/" + fileName;
        Files.write(Paths.get(uploadPath), learningPath.getBytes());
        return fileName;
    }

    private void setBudgetDetail(ClassDto classDto, ClassBatch classBatch) {

        List<BudgetDetail> budgetDB = budgetDetailService.findAllBudgetDetailByStatusAndClassId(active,
                classBatch.getClassId());
        List<BudgetDetail> budgetsFromDto = budgetMapper.toEntities(classDto.getBudgets());
        List<BudgetDetail> budgetRemove = new ArrayList<>();

        if (budgetDB != null && budgetsFromDto != null) {
            List<Integer> collect = budgetsFromDto.stream().map(BudgetDetail::getBudgetDetailId).collect(Collectors.toList());
            for (BudgetDetail b : budgetDB) {
                if (!collect.contains(b.getBudgetDetailId())) {
                    budgetRemove.add(b);
                    budgetsFromDto.remove(b);
                }
            }
        }

        if (budgetsFromDto == null && budgetDB != null) {
            budgetDB.forEach(e -> e.setDeleteFlag(deleted));
        }

        if (budgetsFromDto != null) {
            List<BudgetDetail> collectBudgets = budgetsFromDto.stream()
                    .filter(e -> e.getBudgetSum() != null)
                    .collect(Collectors.toList());
            classBatch.setDetaiBudget(collectBudgets);
            for (BudgetDetail e : collectBudgets) {
                e.setClassBatch(classBatch);
                e.setDeleteFlag(active);
            }
        }

        if (!budgetRemove.isEmpty()) {
            budgetRemove.forEach(e -> e.setDeleteFlag(deleted));
        }
    }

    private void setAudit(ClassDto classDto, ClassBatch classBatch, boolean isNew) {
        List<Audit> auditDb = auditService.findAllAuditByDeleteFlagAndClassId(active, classBatch.getClassId());
        List<Audit> auditsFromDto = auditMapper.toEntities(classDto.getAudits());
        List<Audit> auditRemove = new ArrayList<>();

        if (auditsFromDto == null && !auditDb.isEmpty()) {
            auditDb.forEach(e -> e.setDeleteFlag(deleted));
            classBatch.setStatus(ClassStatusEnum.Draft);
        }

        if (auditDb != null && auditsFromDto != null) {
            List<Integer> collect = auditsFromDto.stream().map(Audit::getAuditId).collect(Collectors.toList());
            for (Audit a : auditDb) {
                if (!collect.contains(a.getAuditId())) {
                    auditRemove.add(a);
                    auditsFromDto.remove(a);
                }
            }
        }

        if (!auditRemove.isEmpty()) {
            auditRemove.forEach(e -> e.setDeleteFlag(deleted));
        }

        if (auditsFromDto != null) {
            List<Audit> collect = auditsFromDto.stream().filter(e -> e.getEventCategory() != null)
                    .collect(Collectors.toList());
            for (Audit e : collect) {
                e.setClassBatch(classBatch);
                e.setDeleteFlag(active);
            }
            if (isNew) {
                classBatch.setStatus(ClassStatusEnum.Draft);
            } else {
                classBatch.setStatus(ClassStatusEnum.InProcess);
            }
            classBatch.setAudit(auditsFromDto);

        }
    }


}
