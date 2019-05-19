package by.andreisergeichyk.service;

import by.andreisergeichyk.dto.comment.CommentRequestDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Comment;
import by.andreisergeichyk.repository.AccountRepository;
import by.andreisergeichyk.repository.BookRepository;
import by.andreisergeichyk.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private CommentRepository commentRepository;
    private BookRepository bookRepository;
    private AccountRepository accountRepository;

    @Autowired
    public CommentService(CommentRepository reviewRepository, BookRepository bookRepository,
                          AccountRepository accountRepository) {
        this.commentRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.accountRepository = accountRepository;
    }

    public List<Comment> findAllByBookId(Serializable bookId) {
        return commentRepository.findAllByBookId(bookId);
    }

    public Comment save(CommentRequestDto requestCommentDto) {
        Optional<Account> account = accountRepository.findByUsername(requestCommentDto.getUsername());
        Book book = bookRepository.findById(requestCommentDto.getBookId()).get();

        return commentRepository.save(Comment.builder()
                .book(book)
                .account(account.get())
                .value(requestCommentDto.getComment())
                .build());
    }

    public void delete(Comment object) {
        commentRepository.delete(object);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> findAll() {
        ArrayList<Comment> reviews = new ArrayList<>();
        Iterable<Comment> result = commentRepository.findAll();
        result.forEach(reviews::add);

        return reviews;
    }
}
