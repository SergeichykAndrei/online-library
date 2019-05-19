package by.andreisergeichyk.service;

import by.andreisergeichyk.dto.account.AccountDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.exception.UsernameExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Account findByName(String username);

    Account registerNewUserAccount(AccountDto accountDto) throws UsernameExistsException;
}
