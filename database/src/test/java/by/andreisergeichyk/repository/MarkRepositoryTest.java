package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class MarkRepositoryTest extends BaseCase {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MarkRepository voteRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void findByBookId() {
        Iterable<Genre> genres = genreRepository.findAll();
        List<Long> genreIds = new ArrayList<>();
        genres.forEach(genre -> genreIds.add(genre.getId()));
        Page<Book> book = bookRepository.findAllByAuthorNameContainingIgnoreCaseAndGenreIdIn("authorThird",
                PageRequest.of(0, 2), genreIds);
        BigDecimal avgVote = voteRepository.avgMark(book.stream().findFirst().get().getId());
        Assert.assertThat(avgVote, equalTo(BigDecimal.valueOf(4.5)));
    }
}
