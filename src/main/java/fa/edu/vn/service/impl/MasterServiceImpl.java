package fa.edu.vn.service.impl;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.dto.dropDownDto.*;
import fa.edu.vn.entites.*;
import fa.edu.vn.repository.*;
import fa.edu.vn.service.IBudgetService;
import fa.edu.vn.service.IClassAdminService;
import fa.edu.vn.service.ILocationService;
import fa.edu.vn.service.IMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements IMasterService {

    private final ISubjectTypeRepository subjectTypeRepository;

    private final IDeliveryTypeRepository deliveryTypeRepository;

    private final IFormatTypeReposity formatTypeRepository;

    private final IScopeRepository scopeRepository;

    private final ISubSubjectTypeRepository subSubjectTypeRepository;

    private final IClassNameRepository classNameRepository;

    private final ITrainerRepository trainerRepository;

    private final IMasterTrainerRepository masterTrainerRepository;

    private final ILocationService locationService;

    private final IClassAdminService classAdminService;

    private final IBudgetService budgetService;

    private final IDetailLocationRepository detailLocationRepository;

    private final ISupplierPartnerRepository supplierPartnerRepository;

    @Override
    public List<SubjectTypeDropDownDto> findSubjectDropDownDto() {
        return subjectTypeRepository.findSubjectDropDownDto();
    }

    @Override
    public List<DeliveryTypeDropDownDto> findDeliveryTypeDropDownDto() {
        return deliveryTypeRepository.findDeliveryDropDownDto();
    }

    @Override
    public List<FormatTypeDropDownDto> findFormatTypeDropDownDto() {
        return formatTypeRepository.findFormatTypeDropDownDto();
    }

    @Override
    public List<ScopeDropDownDto> findScopeDropDownDto() {
        return scopeRepository.findScopeDropDownDtop();
    }

    @Override
    public List<SubSubjectTypeDropDownDto> findSubSubjectTypeDropDown() {
        return subSubjectTypeRepository.findSubSubjectTypeDropDownDto();
    }

    @Override
    public List<ClassNameDropDownDto> findClassNameDropDownDto() {
        return classNameRepository.findClassNameDropDownDto();
    }

    @Override
    public List<TrainerDropDownDto> findTrainerDropDownDto() {
        return trainerRepository.findAllTrainerDropDownDto();
    }

    @Override
    public List<MasterTrainerDropDownDto> findMasterTrainerDropDownDto() {
        return masterTrainerRepository.findAllMasterTrainerDropDownDto();
    }

    @Override
    public List<DetailLocationDropDownDto> findDetailDropDownDto() {
        return detailLocationRepository.findDetailLocationDropDownDto();
    }

    @Override
    public List<SupplierPartnerDropDownDto> findSupplierPartnerDto() {
        return supplierPartnerRepository.findAllSupplierPartnerDto();
    }

    @Override
    public void appendDataForDropDown(Model model, ClassDto classDto) {
        model.addAttribute("classDto", classDto);
        model.addAttribute("classNames", findClassNameDropDownDto());
        model.addAttribute("locations", locationService.findLocationDropDownDtoName());
        model.addAttribute("detailLocations", findDetailDropDownDto());
        model.addAttribute("classAdmin", classAdminService.findAllClassAdminDropDownDto());
        model.addAttribute("budgets", budgetService.findBudgetDropDownDto());
        model.addAttribute("masterTrainers", findMasterTrainerDropDownDto());
        model.addAttribute("trainers", findTrainerDropDownDto());
        model.addAttribute("subjectTypes", findSubjectDropDownDto());
        model.addAttribute("deliveryTypes", findDeliveryTypeDropDownDto());
        model.addAttribute("formatTypes", findFormatTypeDropDownDto());
        model.addAttribute("scopes", findScopeDropDownDto());
        model.addAttribute("subSubjectTypes", findSubSubjectTypeDropDown());
        model.addAttribute("supplierPartner", findSupplierPartnerDto());
    }

    @Override
    public ClassName findClassNameById(Integer id) {
        return classNameRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't Find Class Name"));
    }

    @Override
    public SubjectType findSubjectTypeById(Integer id) {
        return subjectTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find Subject Types"));
    }

    @Override
    public DeliveryType findDeliveryTypeById(Integer id) {
        return deliveryTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find Delivery Type"));
    }

    @Override
    public SubSubjectType findSubSubjectTypeById(Integer id) {
        return subSubjectTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find Sub Subject " +
                "Type"));
    }

    @Override
    public FormatType findFormatTypeById(Integer id) {
        return formatTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't Find Format Type"));
    }

    @Override
    public Scope findScopeById(Integer id) {
        return scopeRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't Find Scopes"));
    }

    @Override
    public Trainer findTrainerById(Integer id) {
        return trainerRepository.findById(id).orElseThrow(() -> new RuntimeException("Cant find Trainer"));
    }

    @Override
    public MasterTrainer findMasterTrainerById(Integer id) {
        return masterTrainerRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find Master Trainer"));
    }

    @Override
    public SupplierPartner findSupplierPartnerById(Integer id) {
        return supplierPartnerRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find " +
                "Supplier Partner"));
    }

}
