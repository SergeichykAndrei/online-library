package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountRepositoryTest extends BaseCase {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByUserId() {
        Optional<Account> user = accountRepository.findByUsernameAndPassword("Petr", "admin");
        Assert.assertTrue(user.isPresent());
    }
}
