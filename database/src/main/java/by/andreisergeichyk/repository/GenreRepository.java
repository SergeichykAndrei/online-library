package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Genre;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByName(String genreName);

    List<Genre> findAllByIdIs(List<Serializable> ids);
}
