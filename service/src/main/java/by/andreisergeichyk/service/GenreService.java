package by.andreisergeichyk.service;

import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.repository.BookRepository;
import by.andreisergeichyk.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@CacheConfig(cacheNames = "genre")
public class GenreService {

    private GenreRepository genreRepository;
    private BookRepository bookRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository, BookRepository bookRepository) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
    }

    public Genre findByName(String genreName) {
        return genreRepository.findByName(genreName).orElse(null);
    }

    public Genre save(Genre genre) {
        Optional<Genre> findedGenre = genreRepository.findByName(genre.getName());
        if (findedGenre.isPresent()) {
            return null;
        }

        return genreRepository.save(genre);
    }

    @CacheEvict
    public int delete(Long genreId) {
        int result = 0;
        Optional<Book> findBook = bookRepository.findFirstByGenreId(genreId);
        if (findBook.isPresent()) {
            return result;
        }
        genreRepository.deleteById(genreId);
        result = 1;

        return result;
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Cacheable
    public List<Genre> findAll() {
        Iterable<Genre> result = genreRepository.findAll();
        ArrayList<Genre> genres = new ArrayList<>();
        result.forEach(genres::add);

        return genres;
    }
}
