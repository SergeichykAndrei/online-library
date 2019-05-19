package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, BookByGenreRepository {

    Optional<Book> findById(Serializable bookId);

    Page<Book> findAllBy(Pageable pageable);

    Optional<Book> findFirstByAuthorId(Serializable authorId);

    Optional<Book> findFirstByGenreId(Serializable genreId);

    Page<Book> findAllByAuthorNameContainingIgnoreCaseAndGenreIdIn(String bookName, Pageable pageable, List<Long> genreIds);

    Page<Book> findBooksByNameStartingWithIgnoreCaseAndGenreIdIn(String start, Pageable pageable, List<Long> genreIds);

    Page<Book> findAllByNameContainingIgnoreCaseAndGenreIdIn(String bookName, Pageable pageable, List<Long> genreIds);

    @Query("select b from Book b join b.marks m group by b.id order by avg(m.value)")
    List<Book> findByRating();
}
