package by.andreisergeichyk.service;

import by.andreisergeichyk.entity.Author;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.repository.AuthorRepository;
import by.andreisergeichyk.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author save(Author author) {
        Optional<Author> findedAuthor = authorRepository.findByName(author.getName());
        if (findedAuthor.isPresent()) {
            return null;
        }

        return authorRepository.save(author);
    }

    public int delete(Long authorId) {
        int result = 0;
        Optional<Book> findBook = bookRepository.findFirstByAuthorId(authorId);
        if (findBook.isPresent()) {
            return result;
        }
        authorRepository.deleteById(authorId);
        result = 1;

        return result;
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> findAll() {
        Iterable<Author> result = authorRepository.findAll();
        ArrayList<Author> authors = new ArrayList<>();
        result.forEach(authors::add);

        return authors;
    }
}
