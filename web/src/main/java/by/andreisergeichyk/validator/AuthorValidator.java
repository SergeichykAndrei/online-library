package by.andreisergeichyk.validator;

import by.andreisergeichyk.entity.Author;
import by.andreisergeichyk.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AuthorValidator implements CustomeValidator<Author> {

    @Override
    public void check(Author author, Errors errors) {
        if (StringUtil.isEmpty(author.getName())) {
            errors.rejectValue("authorName", "450", "author.name.error");
        }
    }
}
