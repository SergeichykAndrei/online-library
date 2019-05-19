package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUsernameAndPassword(String userName, String pasword);

    Optional<Account> findByUsername(String name);
}
