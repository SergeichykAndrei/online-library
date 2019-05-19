package by.andreisergeichyk.util;

import by.andreisergeichyk.dto.book.BookInternalizationFieldDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Component
public class BookHelper {

    private MessageSource messageSource;
    private SessionLocaleResolver sessionLocaleResolver;

    @Autowired
    public BookHelper(MessageSource messageSource, SessionLocaleResolver sessionLocaleResolver) {
        this.messageSource = messageSource;
        this.sessionLocaleResolver = sessionLocaleResolver;
    }

    public BookInternalizationFieldDto getInternalizationField(HttpServletRequest request) {
        return BookInternalizationFieldDto.builder()
                .bookMark(messageSource.getMessage("main.book.mark", null, sessionLocaleResolver.resolveLocale(request)))
                .bookGenre(messageSource.getMessage("main.book.genre", null, sessionLocaleResolver.resolveLocale(request)))
                .numberCopiesBook(messageSource.getMessage("main.book.number.copies", null, sessionLocaleResolver.resolveLocale(request)))
                .bookAuthor(messageSource.getMessage("main.book.author", null, sessionLocaleResolver.resolveLocale(request)))
                .build();
    }
}
