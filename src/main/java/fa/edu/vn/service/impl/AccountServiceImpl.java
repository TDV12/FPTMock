package fa.edu.vn.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fa.edu.vn.dto.RegisterDto;
import fa.edu.vn.entites.Account;
import fa.edu.vn.entites.AccountRole;
import fa.edu.vn.entites.Role;
import fa.edu.vn.repository.IAccountRepository;
import fa.edu.vn.repository.IBudgetRepository;
import fa.edu.vn.repository.IChannelRepository;
import fa.edu.vn.repository.IClassAdminRepository;
import fa.edu.vn.repository.IClassNameRepository;
import fa.edu.vn.repository.IDeliveryTypeRepository;
import fa.edu.vn.repository.IFacultyRepository;
import fa.edu.vn.repository.IFormatTypeReposity;
import fa.edu.vn.repository.ILocationRepository;
import fa.edu.vn.repository.IMasterTrainerRepository;
import fa.edu.vn.repository.IScopeRepository;
import fa.edu.vn.repository.ISubSubjectTypeRepository;
import fa.edu.vn.repository.ISubjectTypeRepository;
import fa.edu.vn.repository.ISupplierPartnerRepository;
import fa.edu.vn.repository.ITrainerRepository;
import fa.edu.vn.repository.IUniversityRepository;
import fa.edu.vn.service.IAccountService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	IAccountRepository iAccountRepository;
	

	
	@Override
	public boolean createAccount(RegisterDto registerDto) {
		System.out.println(registerDto);
		Optional<Account> findByAccountName = iAccountRepository.findByAccountName(registerDto.getUserNameRe());
		Account account = new Account(registerDto.getUserNameRe(),passwordEncoder.encode(registerDto.getUserPasswordRe()),new ArrayList<>());
		List<AccountRole> accountRoles =  new ArrayList<>(Arrays.asList(new AccountRole(account, new Role("ROLE_FAManager"))));
		account.setAccountRoles(accountRoles);
		
		if (findByAccountName.isPresent()) {
			return false;
		}else {
			iAccountRepository.save(account);
			return true;
		}

	}

}
