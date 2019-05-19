package by.andreisergeichyk.controller;

import by.andreisergeichyk.entity.Author;
import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.service.AuthorService;
import by.andreisergeichyk.service.GenreService;
import by.andreisergeichyk.validator.AuthorValidator;
import by.andreisergeichyk.validator.GenreValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HandBookController {

    private GenreService genreService;
    private AuthorService authorService;
    private AuthorValidator authorValidators;
    private GenreValidator genreValidator;

    @Autowired
    public HandBookController(GenreService genreService, AuthorService authorService,
                              AuthorValidator authorValidators, GenreValidator genreValidator) {
        this.genreService = genreService;
        this.authorService = authorService;
        this.authorValidators = authorValidators;
        this.genreValidator = genreValidator;
    }

    @InitBinder({"genre"})
    public void initBinderGenre(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(genreValidator);
    }

    @InitBinder({"author"})
    public void initBinderAuthor(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(authorValidators);
    }

    @ModelAttribute("genres")
    public List<Genre> allGenres() {
        return genreService.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> allAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/handbooks")
    public String handBooksPage() {
        return "handbooks";
    }

    @GetMapping("/handbooks/authors")
    public String authorsPage() {
        return "authors-page";
    }

    @GetMapping("/handbooks/authors/authorDelete")
    public String authorDelete(Model model, Long authorId) {
        int deleteResult = authorService.delete(authorId);

        if (deleteResult == 0) {
            model.addAttribute("resultMessage", "author.delete.error");

            return "authors-page";
        }

        return "main-page";
    }

    @GetMapping("/handbooks/authors/addAuthor")
    public String getPageAddAuthor(Model model) {
        model.addAttribute("author", new Author());

        return "add-author";
    }

    @PostMapping("/handbooks/authors/addAuthor")
    public String addAuthor(@Valid @ModelAttribute("author") Author addAuthor,
                            BindingResult result, Errors err) {
        Author saved;
        if (result.hasErrors()) {
            return "add-author";
        }
        saved = authorService.save(addAuthor);
        if (saved == null) {
            result.rejectValue("name", "467", "author.is.exist.error");

            return "add-author";
        }

        return "redirect:/main";
    }

    @GetMapping("/handbooks/genres")
    public String genresPage() {
        return "genres-page";
    }

    @GetMapping("/handbooks/genres/genreDelete")
    public String genreDelete(Model model, Long genreId) {
        int deleteResult = genreService.delete(genreId);

        if (deleteResult == 0) {
            model.addAttribute("resultMessage", "genre.delete.error");

            return "genres-page";
        }

        return "redirect:/main";
    }

    @GetMapping("/handbooks/genres/addGenre")
    public String getPageAddGenre(Model model) {
        model.addAttribute("genre", new Genre());

        return "add-genre";
    }

    @PostMapping("/handbooks/genres/addGenre")
    public String addGenre(@Valid @ModelAttribute("genre") Genre addGenre, BindingResult result, Errors err) {
        Genre saved;
        if (result.hasErrors()) {
            return "add-genre";
        }
        saved = genreService.save(addGenre);
        if (saved == null) {
            result.rejectValue("name", "468", "genre.is.exist.error");

            return "add-genre";
        }

        return "redirect:/main";
    }
}
