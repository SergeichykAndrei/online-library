package by.andreisergeichyk.service;

import by.andreisergeichyk.converter.ModelToDtoConverter;
import by.andreisergeichyk.dto.book.ViewFullInfoBookDto;
import by.andreisergeichyk.dto.book.ViewMainInfoBookDto;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.repository.BookRepository;
import by.andreisergeichyk.repository.CommentRepository;
import by.andreisergeichyk.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;
    private CommentRepository commentRepository;
    private MarkRepository markRepository;
    private ModelToDtoConverter modelToDtoConverter;

    @Autowired
    public BookService(BookRepository bookRepository, ModelToDtoConverter modelToDtoConverter,
                       CommentRepository commentRepository, MarkRepository markRepository) {
        this.commentRepository = commentRepository;
        this.markRepository = markRepository;
        this.bookRepository = bookRepository;
        this.modelToDtoConverter = modelToDtoConverter;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long bookId) {
        commentRepository.deleteAllByBookId(bookId);
        markRepository.deleteAllByBookId(bookId);
        bookRepository.deleteById(bookId);
    }

    public ViewFullInfoBookDto findById(Long id) {
        return bookRepository.findById(id)
                .map(book -> modelToDtoConverter.bookToFullInfoBookDto(book))
                .orElse(null);
    }

    public Book findByIdNative(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<ViewMainInfoBookDto> findAllMainInfo() {
        ArrayList<ViewMainInfoBookDto> list = new ArrayList<>();
        Iterable<Book> books = bookRepository.findAll();
        books.forEach(it -> list.add(modelToDtoConverter.bookToMainInfoBookDto(it)));

        return list;
    }

    public List<ViewFullInfoBookDto> findAllFullInfo() {
        ArrayList<ViewFullInfoBookDto> list = new ArrayList<>();
        Iterable<Book> books = bookRepository.findAll();
        books.forEach(it -> list.add(modelToDtoConverter.bookToFullInfoBookDto(it)));

        return list;
    }

    public Page<ViewMainInfoBookDto> findAllBy(Pageable pageable) {
        return bookRepository.findAllBy(pageable).map(it -> modelToDtoConverter.bookToMainInfoBookDto(it));
    }

    public Page<ViewMainInfoBookDto> findAllByGenreId(Long genreId, Pageable pageable) {
        return bookRepository.findAllByGenreId(genreId, pageable).map(it -> modelToDtoConverter.bookToMainInfoBookDto(it));
    }

    public Page<ViewMainInfoBookDto> findAllByAuthorNameContainingIgnoreCase(String authorName, Pageable pageable,
                                                                             List<Long> genreIds) {
        return bookRepository.findAllByAuthorNameContainingIgnoreCaseAndGenreIdIn(authorName, pageable, genreIds)
                .map(it -> modelToDtoConverter.bookToMainInfoBookDto(it));
    }

    public Page<ViewMainInfoBookDto> findBookByNameContainingIgnoreCase(String bookName, Pageable pageable,
                                                                        List<Long> genreIds) {
        return bookRepository.findAllByNameContainingIgnoreCaseAndGenreIdIn(bookName, pageable, genreIds)
                .map(book -> modelToDtoConverter.bookToMainInfoBookDto(book));
    }

    public Page<ViewMainInfoBookDto> findBooksByNameStartingWithIgnoreCase(String letter, Pageable pageable,
                                                                           List<Long> genreIds) {
        return bookRepository.findBooksByNameStartingWithIgnoreCaseAndGenreIdIn(letter, pageable, genreIds)
                .map(it -> modelToDtoConverter.bookToMainInfoBookDto(it));
    }

    public List<Book> findByRating() {
        return bookRepository.findByRating();
    }
}
