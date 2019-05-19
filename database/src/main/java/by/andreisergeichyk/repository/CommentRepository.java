package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByBookId(Serializable bookId);

    @Modifying
    @Query("delete from Comment c where c.book = :bookId")
    Integer deleteAllByBookId(@Param("bookId") Long bookId);
}
