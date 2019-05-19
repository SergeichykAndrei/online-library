package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Author;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookRepositoryTest extends BaseCase {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void saveBook() {
        Genre genre = new Genre("Художественный3");
        Author author = new Author("А.Дюма3");
        Book book = new Book("Граф Монте-Кристо3", genre, author, "image3", 133, "description3");

        genre = genreRepository.save(genre);
        assertNotNull("Enyity is null", genre);
        author = authorRepository.save(author);
        assertNotNull("Entity is null", author);
        book = bookRepository.save(book);
        assertNotNull("Entity is null", book);
    }

    @Test
    public void findBook() {
        Iterable<Genre> genres = genreRepository.findAll();
        List<Long> genreIds = new ArrayList<>();
        genres.forEach(genre -> genreIds.add(genre.getId()));
        Page<Book> books = bookRepository.findAllByNameContainingIgnoreCaseAndGenreIdIn("Java",
                PageRequest.of(0, 2), genreIds);
        int numberOfElements = books.getNumberOfElements();
        int expectedSize = 1;
        assertEquals(numberOfElements, expectedSize);
    }

    @Test
    public void findByAuthorName() {
        Iterable<Genre> genres = genreRepository.findAll();
        List<Long> genreIds = new ArrayList<>();
        genres.forEach(genre -> genreIds.add(genre.getId()));
        Page<Book> books = bookRepository.findAllByAuthorNameContainingIgnoreCaseAndGenreIdIn("authorSecond",
                PageRequest.of(0, 2), genreIds);
        int numberOfElements = books.getNumberOfElements();
        int expectedSize = 1;
        Assert.assertEquals(numberOfElements, expectedSize);
    }

    @Test
    public void findByGenreId() {
        Optional<Genre> genre = genreRepository.findByName("Научный");
        assertTrue(genre.isPresent());
        Long genreId = genre.get().getId();
        Page<Book> results = bookRepository.findAllByGenreId(genreId, PageRequest.of(0, 2));
        int expectedSize = 2;
        assertEquals(results.getNumberOfElements(), expectedSize);
        List<String> names = results.stream().map(Book::getName).collect(toList());
        assertThat(names, contains("Java", "C+"));
    }

    @Test
    public void findByLetter() {
        Iterable<Genre> genres = genreRepository.findAll();
        List<Long> genreIds = new ArrayList<>();
        genres.forEach(genre -> genreIds.add(genre.getId()));
        Page<Book> results = bookRepository.findBooksByNameStartingWithIgnoreCaseAndGenreIdIn("c",
                PageRequest.of(0, 2), genreIds);
        int numberOfElements = results.getNumberOfElements();
        int expectedSize = 1;
        assertEquals(numberOfElements, expectedSize);
        List<String> names = results.stream().map(Book::getName).collect(toList());
        assertThat(names, contains("C+"));
    }

    @Test
    public void findByRating() {
        List<Book> results = bookRepository.findByRating();
        assertThat(results, hasSize(2));
        List<String> names = results.stream().map(Book::getName).collect(toList());
        assertThat(names, contains("C+", "Java"));
    }

    @Test
    public void findAll() {
        Page<Book> books = bookRepository.findAllBy(PageRequest.of(0, 2));
        System.out.println(books);
        List<Book> values = new ArrayList<>();
        books.forEach(values::add);
        assertThat(values, hasSize(2));
    }
}
