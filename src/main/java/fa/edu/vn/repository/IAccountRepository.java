package fa.edu.vn.repository;

import fa.edu.vn.entites.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findByAccountName(String accountName);
}
