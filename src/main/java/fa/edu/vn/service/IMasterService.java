package fa.edu.vn.service;

import fa.edu.vn.dto.ClassDto;
import fa.edu.vn.dto.dropDownDto.*;
import fa.edu.vn.entites.*;
import fa.edu.vn.enums.TraineeStatusEnum;
import org.springframework.ui.Model;

import java.util.List;

public interface IMasterService {

    List<SubjectTypeDropDownDto> findSubjectDropDownDto();

    List<DeliveryTypeDropDownDto> findDeliveryTypeDropDownDto();

    List<FormatTypeDropDownDto> findFormatTypeDropDownDto();

    List<ScopeDropDownDto> findScopeDropDownDto();

    List<SubSubjectTypeDropDownDto> findSubSubjectTypeDropDown();

    List<ClassNameDropDownDto> findClassNameDropDownDto();

    List<TrainerDropDownDto> findTrainerDropDownDto();

    List<MasterTrainerDropDownDto> findMasterTrainerDropDownDto();

    List<DetailLocationDropDownDto> findDetailDropDownDto();

    List<SupplierPartnerDropDownDto> findSupplierPartnerDto();

    void appendDataForDropDown(Model model, ClassDto classDto);

    ClassName findClassNameById(Integer id);

    SubjectType findSubjectTypeById(Integer id);

    DeliveryType findDeliveryTypeById(Integer id);

    SubSubjectType findSubSubjectTypeById(Integer id);

    FormatType findFormatTypeById(Integer id);

    Scope findScopeById(Integer id);

    Trainer findTrainerById(Integer id);

    MasterTrainer findMasterTrainerById(Integer id);

    SupplierPartner findSupplierPartnerById(Integer id);
}
