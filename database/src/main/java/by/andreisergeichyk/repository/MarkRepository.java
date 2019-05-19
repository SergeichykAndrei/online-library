package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Mark;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface MarkRepository extends CrudRepository<Mark, Long> {

    List<Mark> findAllByBookId(Serializable bookId);

    @Query("select avg (m.value) from Mark m join m.book b where b.id = :bookId")
    BigDecimal avgMark(@Param("bookId") Serializable bookId);

    @Modifying
    @Query("delete from Mark m where m.book = :bookId")
    Integer deleteAllByBookId(@Param("bookId") Long bookId);
}
