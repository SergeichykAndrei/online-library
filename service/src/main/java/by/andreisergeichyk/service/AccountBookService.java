package by.andreisergeichyk.service;

import by.andreisergeichyk.converter.ModelToDtoConverter;
import by.andreisergeichyk.dto.accountbook.AccountBookDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.entity.AccountBook;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.repository.AccountBookRepository;
import by.andreisergeichyk.repository.AccountRepository;
import by.andreisergeichyk.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountBookService {

    private static final long NUMBER_OF_DAYS_UNTIL_RETURN = 10L;
    private static final int ONE_BOOK = 1;

    private AccountBookRepository accountBookRepository;
    private AccountRepository accountRepository;
    private BookRepository bookRepository;
    private ModelToDtoConverter modelToDtoConverter;

    @Autowired
    public AccountBookService(AccountBookRepository accountBookRepository, AccountRepository accountRepository,
                              BookRepository bookRepository, ModelToDtoConverter modelToDtoConverter) {
        this.accountBookRepository = accountBookRepository;
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
        this.modelToDtoConverter = modelToDtoConverter;
    }

    public AccountBook save(String username, Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Account> account = accountRepository.findByUsername(username);

        book.get().setNumberCopies(book.get().getNumberCopies() - ONE_BOOK);
        AccountBook accountBook = AccountBook.builder()
                .book(book.get())
                .account(account.get())
                .dateIssue(LocalDate.now(ZoneId.of("Europe/Minsk")))
                .dateReturn(LocalDate.now(ZoneId.of("Europe/Minsk")).plus(NUMBER_OF_DAYS_UNTIL_RETURN, ChronoUnit.DAYS))
                .build();

        return accountBookRepository.save(accountBook);
    }

    public List<AccountBookDto> findAllByUsername(String username) {
        return modelToDtoConverter.accountBookToAccountBookDto(accountBookRepository.findAllByAccountUsername(username));
    }

    public void delete(Long bookId) {
        Optional<AccountBook> accountBook = accountBookRepository.findFirstByBookId(bookId);
        if (accountBook.isPresent()) {
            Book book = accountBook.get().getBook();
            book.setNumberCopies(book.getNumberCopies() + ONE_BOOK);
            accountBookRepository.delete(accountBook.get());
        }
    }

    public List<AccountBookDto> findAll() {
        List<AccountBookDto> accountBooks = new ArrayList<>();
        accountBookRepository.findAll()
                .forEach(accountBook -> accountBooks.add(modelToDtoConverter.accountBookToAccountBookDto(accountBook)));

        return accountBooks;
    }

    public AccountBook findFirstByBookId(Long bookId) {
        return accountBookRepository.findFirstByBookId(bookId).orElse(null);
    }
}
