package by.andreisergeichyk.validator;

import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class GenreValidator implements CustomeValidator<Genre> {

    @Override
    public void check(Genre genre, Errors errors) {
        if (StringUtil.isEmpty(genre.getName())) {
            errors.rejectValue("genreName", "449", "genre.name.error");
        }
    }
}
