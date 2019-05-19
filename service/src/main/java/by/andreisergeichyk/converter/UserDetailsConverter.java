package by.andreisergeichyk.converter;

import by.andreisergeichyk.entity.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter {

    public UserDetails convert(Account account) {
        return User
                .builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities(new SimpleGrantedAuthority(account.getRole().getName()))
                .roles(account.getRole().getName())
                .build();
    }
}
