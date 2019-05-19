package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookByGenreRepository {

    Page<Book> findAllByGenreId(Long genreId, Pageable pageable);
}
