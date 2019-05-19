package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Author;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AuthorRepositoryTest extends BaseCase {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void saveAuthor() {
        Author author = new Author("A.Дюма1");
        author = authorRepository.save(author);
        Assert.assertNotNull("Entity is null", author);
    }

    @Test
    public void findAuthor() {
        Author author = new Author("A.Дюма2");
        author = authorRepository.save(author);
        Assert.assertNotNull("Entity is null", author);
        Optional<Author> duma = authorRepository.findById(author.getId());
        assertTrue(duma.isPresent());
    }

    @Test
    public void findAll() {
        Iterable<Author> authors = authorRepository.findAll();
        List<Author> values = new ArrayList<>();
        authors.forEach(values::add);
        assertThat(values, hasSize(2));
        List<String> names = values.stream().map(Author::getName).collect(toList());
        assertThat(names, contains("authorSecond", "authorThird"));
    }
}
