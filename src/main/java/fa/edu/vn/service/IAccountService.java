package fa.edu.vn.service;

import fa.edu.vn.dto.RegisterDto;

public interface IAccountService {

	boolean createAccount(RegisterDto registerDto);
}
