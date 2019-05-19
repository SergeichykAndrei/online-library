package by.andreisergeichyk.util;

import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.entity.Author;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Comment;
import by.andreisergeichyk.entity.Contact;
import by.andreisergeichyk.entity.Genre;
import by.andreisergeichyk.entity.Mark;
import by.andreisergeichyk.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public final class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void deleteTestData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Mark ").executeUpdate();
        entityManager.createQuery("delete from Comment ").executeUpdate();
        entityManager.createQuery("delete from Book ").executeUpdate();
        entityManager.createQuery("delete from Account ").executeUpdate();
        entityManager.createQuery("delete from Role ").executeUpdate();
        entityManager.createQuery("delete from Genre ").executeUpdate();
        entityManager.createQuery("delete from Author ").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Author authorSecond = new Author("authorSecond");
        Author authorThird = new Author("authorThird");
        entityManager.persist(authorSecond);
        entityManager.persist(authorThird);

        Role admin = new Role("admin");
        Role user = new Role("user");
        entityManager.persist(admin);
        entityManager.persist(user);


        Account petr = new Account("Petr", "admin", new Contact("1265838", "admin@mail.com"));
        Account ivan = new Account("Ivan", "user", new Contact("2334538", "user@mail.com"));
        entityManager.persist(petr);
        entityManager.persist(ivan);

        Genre scientific = new Genre("Научный");
        Genre comedy = new Genre("Комедия");
        entityManager.persist(scientific);
        entityManager.persist(comedy);

        Book bookFirst = new Book("Java", scientific, authorThird, "image", 12, "description");
        Book bookSecond = new Book("C+", scientific, authorThird, "image", 22, "description");
        Book bookThird = new Book("Комедия", comedy, authorSecond, "image", 14, "description");
        entityManager.persist(bookFirst);
        entityManager.persist(bookSecond);
        entityManager.persist(bookThird);

        Comment reviewFirst = new Comment("1", bookFirst, petr);
        Comment reviewSecond = new Comment("1", bookSecond, ivan);
        Comment reviewThird = new Comment("1", bookThird, ivan);
        entityManager.persist(reviewFirst);
        entityManager.persist(reviewSecond);
        entityManager.persist(reviewThird);

        Mark voteFirst = new Mark(5, bookFirst);
        Mark voteSecond = new Mark(4, bookFirst);
        Mark voteThird = new Mark(3, bookSecond);
        entityManager.persist(voteFirst);
        entityManager.persist(voteSecond);
        entityManager.persist(voteThird);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
