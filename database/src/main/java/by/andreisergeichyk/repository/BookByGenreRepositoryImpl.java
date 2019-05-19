package by.andreisergeichyk.repository;

import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Book_;
import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.entity.Genre_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookByGenreRepositoryImpl implements BookByGenreRepository {

    private EntityManager entityManager;

    @Autowired
    public BookByGenreRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<Book> findAllByGenreId(Long genreId, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        Join<Book, Genre> genreJoin = root.join(Book_.genre);
        criteria.select(root).where(cb.equal(genreJoin.get(Genre_.id), genreId));

        List<Book> books = entityManager.createQuery(criteria).getResultList();

        int totalElements = books.size();

        books = entityManager.createQuery(criteria)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        return new PageImpl<Book>(books, pageable, totalElements);
    }
}
