package fa.edu.vn.security;

import fa.edu.vn.entites.Account;
import fa.edu.vn.entites.AccountRole;
import fa.edu.vn.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByAccountName(username);
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Invalid User Name or User Password");
        }
        Account account = optionalAccount.get();
        return new User(account.getAccountName(), account.getAccountPassword(), account.getAccountRoles()
                .stream()
                .map((e) -> new SimpleGrantedAuthority(e.getRole().getRole().toString())).collect(Collectors.toList()));
    }
}
