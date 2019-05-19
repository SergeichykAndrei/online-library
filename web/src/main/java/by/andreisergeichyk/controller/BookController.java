package by.andreisergeichyk.controller;

import by.andreisergeichyk.entity.Author;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.service.AccountBookService;
import by.andreisergeichyk.service.AuthorService;
import by.andreisergeichyk.service.BookService;
import by.andreisergeichyk.service.GenreService;
import by.andreisergeichyk.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {

    private GenreService genreService;
    private AuthorService authorService;
    private BookValidator bookValidator;
    private BookService bookService;
    private AccountBookService accountBookService;

    @Autowired
    public BookController(GenreService genreService, AuthorService authorService,
                          BookValidator bookValidator, BookService bookService,
                          AccountBookService accountBookService) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookValidator = bookValidator;
        this.bookService = bookService;
        this.accountBookService = accountBookService;
    }

    @InitBinder()
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(bookValidator);
    }

    @ModelAttribute("genres")
    public List<Genre> allGenres() {
        return genreService.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> allAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());

        return "add-book";
    }

    @GetMapping("/editBook")
    public String editBook(Model model, Long bookId) {
        Book book = bookService.findByIdNative(bookId);
        model.addAttribute("book", book);

        return "add-book";
    }

    @PostMapping("/addBook")
    public String addBook(Model model, @Valid Book addBook, Errors err) {
        if (err.hasErrors()) {
            return "add-book";
        }
        addBook = bookService.save(addBook);
        model.addAttribute("bookName", addBook.getName());
        model.addAttribute("bookId", addBook.getId());

        return "save-success";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(Model model, Long bookId) {
        if (Objects.isNull(accountBookService.findFirstByBookId(bookId))) {
            bookService.delete(bookId);

            return "delete-success-book";
        }
        String responseMessage = "book.response.error";
        model.addAttribute("responseMessage", responseMessage);
        model.addAttribute("book", bookService.findById(bookId));

        return "book-info";
    }
}
