package by.andreisergeichyk.validator;

import by.andreisergeichyk.dto.account.AccountDto;
import by.andreisergeichyk.util.StringUtil;
import by.andreisergeichyk.util.matcher.EmailMather;
import by.andreisergeichyk.util.matcher.PhoneMather;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AccountValidator implements CustomeValidator<AccountDto> {

    @Override
    public void check(AccountDto accountDto, Errors errors) {
        if (StringUtil.isEmpty(accountDto.getUsername())) {
            errors.rejectValue("username", "448", "account.username.error");
        }
        if (StringUtil.isEmpty(accountDto.getPassword()) || accountDto.getPassword().length() < 8) {
            errors.rejectValue("password", "450", "account.password.error");
        }
        if (!EmailMather.validateEmail(accountDto.getEmail())) {
            errors.rejectValue("email", "451", "account.email.error");
        }
        if (!PhoneMather.validatePhone(accountDto.getPhone())) {
            errors.rejectValue("phone", "452", "account.phone.error");
        }
    }
}
