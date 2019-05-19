package by.andreisergeichyk.validator;

import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import static java.util.Objects.isNull;

@Component
public class BookValidator implements CustomeValidator<Book> {

    @Override
    public void check(Book book, Errors errors) {
        if (StringUtil.isEmpty(book.getName())) {
            errors.rejectValue("name", "444", "book.name.error");
        }
        if (isNull(book.getNumberCopies())) {
            errors.rejectValue("numberCopies", "445", "book.number.copies.error");
        }
        if (!isNull(book.getNumberCopies()) && (book.getNumberCopies() < 0 || !(book.getNumberCopies() instanceof Integer))) {
            errors.rejectValue("numberCopies", "445", "book.number.copies.error");
        }
        if (StringUtil.isEmpty(book.getImage())) {
            errors.rejectValue("image", "446", "book.image.error");
        }
        if (StringUtil.isEmpty(book.getDescription())) {
            errors.rejectValue("description", "447", "book.description.error");
        }
    }
}
