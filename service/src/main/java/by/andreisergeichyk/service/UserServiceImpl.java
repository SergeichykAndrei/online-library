package by.andreisergeichyk.service;

import by.andreisergeichyk.converter.ModelToDtoConverter;
import by.andreisergeichyk.converter.UserDetailsConverter;
import by.andreisergeichyk.dto.account.AccountDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.exception.UsernameExistsException;
import by.andreisergeichyk.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private AccountRepository accountRepository;
    private UserDetailsConverter detailsConverter;
    private ModelToDtoConverter modelToDtoConverter;

    @Autowired
    public UserServiceImpl(AccountRepository accountRepository, UserDetailsConverter detailsConverter,
                           ModelToDtoConverter modelToDtoConverter) {
        this.accountRepository = accountRepository;
        this.detailsConverter = detailsConverter;
        this.modelToDtoConverter = modelToDtoConverter;
    }

    @Override
    public Account findByName(String name) {
        return accountRepository.findByUsername(name).orElse(null);
    }

    @Override
    public Account registerNewUserAccount(AccountDto accountDto) throws UsernameExistsException {
        if (usernameExist(accountDto.getUsername())) {
            throw new UsernameExistsException(accountDto.getUsername());
        }

        return accountRepository.save(modelToDtoConverter.accountDtoToAccount(accountDto));
    }

    private boolean usernameExist(String username) {
        Optional<Account> account = accountRepository.findByUsername(username);

        return account.isPresent();
    }

    public Account save(Account object) {
        return accountRepository.save(object);
    }

    public void delete(Account object) {
        accountRepository.delete(object);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> findAll() {
        Iterable<Account> result = accountRepository.findAll();
        ArrayList<Account> users = new ArrayList<>();
        result.forEach(users::add);

        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return Optional.of(name)
                .map(it -> accountRepository.findByUsername(name))
                .map(it -> detailsConverter.convert(it.get()))
                .orElseThrow(() -> new UsernameNotFoundException("Account does not exist!"));
    }
}
