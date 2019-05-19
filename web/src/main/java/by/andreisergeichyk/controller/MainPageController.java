package by.andreisergeichyk.controller;

import by.andreisergeichyk.dto.book.BookResponseDto;
import by.andreisergeichyk.dto.book.ViewMainInfoBookDto;
import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.service.BookService;
import by.andreisergeichyk.service.GenreService;
import by.andreisergeichyk.util.BookHelper;
import by.andreisergeichyk.entity.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainPageController {

    private static final int AMENDMENT_TO_PAGEABLE = 1;

    private GenreService genreService;
    private BookService bookService;
    private BookHelper bookHelper;

    @Autowired
    public MainPageController(GenreService genreService, BookService bookService, BookHelper bookHelper) {
        this.genreService = genreService;
        this.bookService = bookService;
        this.bookHelper = bookHelper;
    }

    @ModelAttribute("genres")
    public List<Genre> allGenres() {
        return genreService.findAll();
    }

    @GetMapping("/main")
    public String showAllBooksPage(Model model) {
        Searcher searcher = new Searcher(allGenres());
        Page<ViewMainInfoBookDto> books = bookService.findAllBy(PageRequest.of(searcher.getCurrentPage() - AMENDMENT_TO_PAGEABLE,
                searcher.getNumberOfBooksOnPage()));
        model.addAttribute("searcher", searcher);
        model.addAttribute("books", books);

        return "main-page";
    }

    @ResponseBody
    @PostMapping(value = "/main", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookResponseDto showAllBooksWithSearchParamPage(HttpServletRequest req, @RequestBody Searcher searcher) {
        Page<ViewMainInfoBookDto> books;
        if (searcher.isSearchMethod()) {
            books = bookService.findBookByNameContainingIgnoreCase(searcher.getSearchLine(),
                    PageRequest.of(searcher.getCurrentPage() - AMENDMENT_TO_PAGEABLE, searcher.getNumberOfBooksOnPage()),
                    searcher.getGenresIds());
        } else {
            books = bookService.findAllByAuthorNameContainingIgnoreCase(searcher.getSearchLine(),
                    PageRequest.of(searcher.getCurrentPage() - AMENDMENT_TO_PAGEABLE, searcher.getNumberOfBooksOnPage()),
                    searcher.getGenresIds());
        }

        return BookResponseDto.builder()
                .books(books)
                .bookFields(bookHelper.getInternalizationField(req))
                .build();
    }
}
