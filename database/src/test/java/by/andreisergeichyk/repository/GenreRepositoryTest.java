package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Genre;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GenreRepositoryTest extends BaseCase {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void saveGenre() {
        Genre genre = new Genre("Боевик5");
        genre = genreRepository.save(genre);
        Assert.assertNotNull("Entity is Null!", genre);
    }

    @Test
    public void findGenre() {
        Optional<Genre> genre = genreRepository.findByName("Научный");
        Assert.assertTrue(genre.isPresent());
        genreRepository.findById(genre.get().getId());
        assertThat(genre.get().getName(), equalTo("Научный"));
    }

    @Test
    public void findByName() {
        Optional<Genre> genre = genreRepository.findByName("Научный");
        Assert.assertTrue(genre.isPresent());
    }
}
