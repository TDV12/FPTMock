package fa.edu.vn.config;

import fa.edu.vn.entites.*;
import fa.edu.vn.enums.TraineeStatusEnum;
import fa.edu.vn.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InitData {

    private final PasswordEncoder passwordEncoder;

    private final IAccountRepository iAccountRepository;

    private final ILocationRepository locationRepository;

    private final IClassAdminRepository classAdminRepository;

    private final ITrainerRepository trainerRepository;

    private final IChannelRepository channelRepository;

    private final IUniversityRepository universityRepository;

    private final IFacultyRepository facultyRepository;

    private final IBudgetRepository budgetRepository;

    private final ISubjectTypeRepository subjectTypeRepository;

    private final IDeliveryTypeRepository deliveryTypeRepository;

    private final IScopeRepository scopeRepository;

    private final IClassNameRepository classNameRepository;

    private final ISubSubjectTypeRepository subSubjectTypeRepository;

    private final IFormatTypeReposity formatTypeReposity;

    private final IMasterTrainerRepository masterTrainerRepository;

    private final ISupplierPartnerRepository supplierPartnerRepository;



    @GetMapping("/init/login")
    @ResponseBody
    public List<Account> initLogin() {
        Account account1 = new Account("FAmanager", passwordEncoder.encode("12345"), new ArrayList<>());
        List<AccountRole> accountRolesAccount1 = new ArrayList<>(Arrays.asList(new AccountRole(account1, new Role("ROLE_FAManager"))));
        account1.setAccountRoles(accountRolesAccount1);
        Account account2 = new Account("ROLE_DeliveryManager", passwordEncoder.encode("12345"), new ArrayList<>());
        List<AccountRole> accountRolesAccount2 = new ArrayList<>(Arrays.asList(new AccountRole(account2, new Role("ROLE_DeliveryManager"))));
        account2.setAccountRoles(accountRolesAccount2);
        Account account3 = new Account("ROLE_ClassAdmin", passwordEncoder.encode("12345"), new ArrayList<>());
        List<AccountRole> accountRolesAccount3 = new ArrayList<>(Arrays.asList(new AccountRole(account3, new Role("ROLE_ClassAdmin"))));
        account3.setAccountRoles(accountRolesAccount3);
        Account account4 = new Account("ROLE_FARec", passwordEncoder.encode("12345"), new ArrayList<>());
        List<AccountRole> accountRolesAccount4 = new ArrayList<>(Arrays.asList(new AccountRole(account4, new Role("ROLE_FARec"))));
        account4.setAccountRoles(accountRolesAccount4);
        iAccountRepository.saveAll(Arrays.asList(account1, account2, account3, account4));
        return iAccountRepository.findAll();
    }

    @GetMapping("/init/masterData")
    @ResponseBody
    public ResponseEntity<Object> initMasterData() {
        DetailLocation detailLocationHanoi = new DetailLocation("DC:Tran Duy Hung");
        DetailLocation detailLocationDaNang = new DetailLocation("DC:Cau Song Han");
        DetailLocation detailLocation = new DetailLocation("DC:Xa Dan");
        DetailLocation detailLocationHCM = new DetailLocation("DC:Quan 3");
        Location haNoi = new Location("Ha Noi", null, Arrays.asList(detailLocationHanoi, detailLocation));
        Location daNang = new Location("Da Nang", null, Collections.singletonList(detailLocationDaNang));
        Location hcm = new Location("Ho Chi Minh", null, Collections.singletonList(detailLocationHCM));
        detailLocationHanoi.setLocation(haNoi);
        detailLocationDaNang.setLocation(daNang);
        detailLocationHCM.setLocation(hcm);
        detailLocation.setLocation(haNoi);
        List<Location> locations = new ArrayList<>(Arrays.asList(haNoi, daNang, hcm));
        locationRepository.saveAll(locations);
        ClassAdmin admin1 = new ClassAdmin("Admin Dep Trai", null);
        ClassAdmin admin2 = new ClassAdmin("Admin Xinh Gai", null);
        ClassAdminProfile profileAdmin1 = new ClassAdminProfile("Dep Trai Vo Dich Thien Ha", LocalDate.of(1994, 12, 11), 1, "0989164456", "sieudeptrai@gmail.com", null, admin1);
        ClassAdminProfile profileAdmin2 = new ClassAdminProfile("Xinh Gai Vo Dich Thien Ha", LocalDate.of(1994, 12, 11), 1, "0989164457", "hoahautraidat@gmail.com", null, admin2);
        admin1.setClassAdminProfile(profileAdmin1);
        admin2.setClassAdminProfile(profileAdmin2);
        List<ClassAdmin> classAdmins = new ArrayList<>(Arrays.asList(admin1, admin2));
        classAdminRepository.saveAll(classAdmins);
        Trainer trainer1 = new Trainer("unknow", null);
        Trainer trainer2 = new Trainer("unknow", null);
        MasterTrainer masterTrainer1 = new MasterTrainer();
        MasterTrainer masterTrainer2 = new MasterTrainer();
        TrainerProfile masterTrainerProfile = new TrainerProfile("account3", "master trainer", LocalDate.of(1991, 1, 1)
                , 1,
                1, "unknows",
                "0988888822", "masterTrainer1@gmail.com", "unknow", null, masterTrainer1);
        TrainerProfile masterTrainerProfile2 = new TrainerProfile("account4", "master trainer 3", LocalDate.of(1991, 1
                , 1)
                , 1,
                1,
                "unknows", "0988888812", "masterTrainer2@gmail.com", "unknow", null, masterTrainer2);
        masterTrainer1.setTrainerProfile(masterTrainerProfile);
        masterTrainer2.setTrainerProfile(masterTrainerProfile2);
        masterTrainerRepository.saveAll(Arrays.asList(masterTrainer1, masterTrainer2));
        TrainerProfile trainerProfile1 = new TrainerProfile("account1", "fullName", LocalDate.of(1991, 1, 1), 1, 1, "unknows", "0988888888", "trainer1@gmail.com", "unknow", null, trainer1);
        TrainerProfile trainerProfile2 = new TrainerProfile("account2", "fullName", LocalDate.of(1991, 1, 1), 1, 1, "unknows", "0988888882", "trainer2@gmail.com", "unknow", null, trainer2);
        trainer1.setTrainerProfile(trainerProfile1);
        trainer2.setTrainerProfile(trainerProfile2);
        List<Trainer> trainer = new ArrayList<>(Arrays.asList(trainer1, trainer2));
        trainerRepository.saveAll(trainer);
        Channel channel = new Channel(1);
        Channel channel2 = new Channel(2);
        Channel channel3 = new Channel(3);
        List<Channel> channels = new ArrayList<>(Arrays.asList(channel, channel2, channel3));
        channelRepository.saveAll(channels);
        University university = new University("GTVT");
        University university2 = new University("BACH KHOA");
        University university3 = new University("DHQGHN");
        University university4 = new University("SU PHAM");
        List<University> Universitys = new ArrayList<>(Arrays.asList(university, university2, university3, university4));
        List<Faculty> Facultys = new ArrayList<>(Arrays.asList(new Faculty("Cong trinh"), new Faculty("IT"), new Faculty("Kinh Te"), new Faculty("Luat")));
        facultyRepository.saveAll(Facultys);
        universityRepository.saveAll(Universitys);
        List<Budget> budgets = new ArrayList<>(Arrays.asList(new Budget("CTC_Project_ADP"), new Budget(
                "CTC_Fresher_Allowance"), new Budget("CTC_Fresher_Training"), new Budget(
                "CTC_Specific_Fresher_Allowance"), new Budget("CTC_Specific_Fresher_Training"), new Budget(
                "CTC_Specific_Fresher_Training_Award"), new Budget("CTC_FU"), new Budget("CTC_Uni")));
        budgetRepository.saveAll(budgets);
        List<SubjectType> subjectTypes = new ArrayList<>(Arrays.asList(new SubjectType("Company Process"),
                new SubjectType("Standard Process"), new SubjectType("IT Technical"), new SubjectType("Non IT " +
                        "Technical"), new SubjectType("Foreign Language"), new SubjectType("Soft Skill"),
                new SubjectType("Management"), new SubjectType("Organizational Overview O & Culture")));
        subjectTypeRepository.saveAll(subjectTypes);
        List<DeliveryType> deliveryTypes = new ArrayList<>(Arrays.asList(new DeliveryType("Class"), new DeliveryType(
                        "Seminar"), new DeliveryType("Exam"), new DeliveryType("Contest"), new DeliveryType("Certificate"),
                new DeliveryType("Club"), new DeliveryType("OJT"), new DeliveryType("Others")));
        deliveryTypeRepository.saveAll(deliveryTypes);
        List<Scope> scopes = new ArrayList<>(Arrays.asList(new Scope("Company"), new Scope("Unit"), new Scope("Out-side")));
        scopeRepository.saveAll(scopes);
        List<ClassName> classNames = new ArrayList<>(Arrays.asList(new ClassName("Fresher Developer Java"),
                new ClassName("Fresher Developer .Net"), new ClassName("Fresher Developer Angular"), new ClassName(
                        "Fresher Developer React"), new ClassName("Campus Link Developer Java"), new ClassName("Campus " +
                        "Link Developer .Net"), new ClassName("Campus Link Developer React"), new ClassName("Campus " +
                        "Link Developer Angular")));
        classNameRepository.saveAll(classNames);
        List<SubSubjectType> subSubjectTypes = new ArrayList<>(Arrays.asList(new SubSubjectType("Cloud"),
                new SubSubjectType("Big Data"), new SubSubjectType("CAD"), new SubSubjectType("CAE"),
                new SubSubjectType("SAP"), new SubSubjectType("IT General"), new SubSubjectType("Test"),
                new SubSubjectType("Others")));
        subSubjectTypeRepository.saveAll(subSubjectTypes);
        List<FormatType> formatTypes = new ArrayList<>(Arrays.asList(new FormatType("Online"), new FormatType("Offline"), new FormatType("Blended")));
        formatTypeReposity.saveAll(formatTypes);
        List<SupplierPartner> supplierPartners = new ArrayList<>(Arrays.asList(new SupplierPartner("Supplier 1"),
                new SupplierPartner("Supplier 2"), new SupplierPartner("Supplier 3")));
        supplierPartnerRepository.saveAll(supplierPartners);


        return ResponseEntity.ok("init Done");

    }
}


