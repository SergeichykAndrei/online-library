package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Comment;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findAllByBookId(Serializable bookId);
}
