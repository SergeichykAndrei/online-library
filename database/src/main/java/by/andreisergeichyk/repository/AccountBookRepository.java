package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.AccountBook;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface AccountBookRepository extends CrudRepository<AccountBook, Long> {

    List<AccountBook> findAllByAccountUsername(String username);

    Optional<AccountBook> findFirstByBookId(Serializable bookId);
}
