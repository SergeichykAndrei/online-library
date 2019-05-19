package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Comment;
import by.andreisergeichyk.entity.Genre;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class ReviewTest extends BaseCase {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void findByBookId() {
        Iterable<Genre> genres = genreRepository.findAll();
        List<Long> genreIds = new ArrayList<>();
        genres.forEach(genre -> genreIds.add(genre.getId()));
        Page<Book> books = bookRepository.findAllByNameContainingIgnoreCaseAndGenreIdIn("Java",
                PageRequest.of(0, 2), genreIds);
        Long bookId = books.stream().findFirst().get().getId();
        List<Comment> results = commentRepository.findAllByBookId(bookId);
        assertThat(results, hasSize(1));
    }
}
