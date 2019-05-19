package by.andreisergeichyk.converter;

import by.andreisergeichyk.dto.account.AccountDto;
import by.andreisergeichyk.dto.accountbook.AccountBookDto;
import by.andreisergeichyk.dto.book.ViewFullInfoBookDto;
import by.andreisergeichyk.dto.book.ViewMainInfoBookDto;
import by.andreisergeichyk.dto.comment.CommentDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.entity.AccountBook;
import by.andreisergeichyk.entity.Book;
import by.andreisergeichyk.entity.Comment;
import by.andreisergeichyk.entity.Contact;
import by.andreisergeichyk.entity.Role;
import by.andreisergeichyk.formatter.LocalDateFormat;
import by.andreisergeichyk.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelToDtoConverter {

    private MarkRepository markRepository;

    @Autowired
    public ModelToDtoConverter(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public ViewMainInfoBookDto bookToMainInfoBookDto(Book book) {
        if (book.getAuthor() == null && book.getGenre() == null) {
            return ViewMainInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .numberCopies(book.getNumberCopies())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .build();
        }
        if (book.getAuthor() == null) {
            return ViewMainInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .numberCopies(book.getNumberCopies())
                    .genre(book.getGenre().getName())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .build();
        }
        if (book.getGenre() == null) {
            return ViewMainInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .author(book.getAuthor().getName())
                    .numberCopies(book.getNumberCopies())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .build();
        }

        return ViewMainInfoBookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor().getName())
                .numberCopies(book.getNumberCopies())
                .genre(book.getGenre().getName())
                .image(book.getImage())
                .avgMark(markRepository.avgMark(book.getId()))
                .build();
    }

    public ViewFullInfoBookDto bookToFullInfoBookDto(Book book) {
        if (book.getAuthor() == null && book.getGenre() == null && book.getComments() == null) {
            return ViewFullInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .image(book.getImage())
                    .description(book.getDescription())
                    .numberCopies(book.getNumberCopies())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .build();
        }
        if (book.getAuthor() == null && book.getGenre() == null) {
            return ViewFullInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .description(book.getDescription())
                    .numberCopies(book.getNumberCopies())
                    .comments(book.getComments().stream()
                            .map(this::commentToCommentDto)
                            .collect(Collectors.toList()))
                    .build();
        }
        if (book.getGenre() == null && book.getComments() == null) {
            return ViewFullInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .genre(book.getGenre().getName())
                    .description(book.getDescription())
                    .numberCopies(book.getNumberCopies())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .build();
        }
        if (book.getAuthor() == null) {
            return ViewFullInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .genre(book.getGenre().getName())
                    .description(book.getDescription())
                    .numberCopies(book.getNumberCopies())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .comments(book.getComments().stream()
                            .map(this::commentToCommentDto)
                            .collect(Collectors.toList()))
                    .build();
        }
        if (book.getComments() == null) {
            return ViewFullInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .author(book.getAuthor().getName())
                    .genre(book.getGenre().getName())
                    .numberCopies(book.getNumberCopies())
                    .description(book.getDescription())
                    .image(book.getImage())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .build();
        }
        if (book.getGenre() == null) {
            return ViewFullInfoBookDto.builder()
                    .id(book.getId())
                    .name(book.getName())
                    .author(book.getAuthor().getName())
                    .image(book.getImage())
                    .numberCopies(book.getNumberCopies())
                    .description(book.getDescription())
                    .avgMark(markRepository.avgMark(book.getId()))
                    .comments(book.getComments().stream()
                            .map(this::commentToCommentDto)
                            .collect(Collectors.toList()))
                    .build();
        }

        return ViewFullInfoBookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor().getName())
                .genre(book.getGenre().getName())
                .image(book.getImage())
                .numberCopies(book.getNumberCopies())
                .avgMark(markRepository.avgMark(book.getId()))
                .description(book.getDescription())
                .comments(book.getComments().stream()
                        .map(this::commentToCommentDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .value(comment.getValue())
                .accountName(comment.getAccount().getUsername())
                .build();
    }

    public List<CommentDto> commentToCommentDto(List<Comment> comments) {
        return comments.stream()
                .map(comment -> CommentDto.builder().
                        value(comment.getValue())
                        .accountName(comment.getAccount().getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    public AccountBookDto accountBookToAccountBookDto(AccountBook accountBook) {
        return AccountBookDto.builder()
                .bookName(accountBook.getBook().getName())
                .accountName(accountBook.getAccount().getUsername())
                .bookId(accountBook.getBook().getId())
                .dateIssue(LocalDateFormat.format(accountBook.getDateIssue()))
                .dateReturn(LocalDateFormat.format(accountBook.getDateReturn()))
                .build();
    }

    public List<AccountBookDto> accountBookToAccountBookDto(List<AccountBook> accountBooks) {
        return accountBooks.stream()
                .map(accountBook -> AccountBookDto.builder()
                        .bookName(accountBook.getBook().getName())
                        .accountName(accountBook.getAccount().getUsername())
                        .bookId(accountBook.getBook().getId())
                        .dateIssue(LocalDateFormat.format(accountBook.getDateIssue()))
                        .dateReturn(LocalDateFormat.format(accountBook.getDateReturn()))
                        .build())
                .collect(Collectors.toList());
    }

    public Account accountDtoToAccount(AccountDto accountDto) {
        return Account.builder()
                .username(accountDto.getUsername())
                .password(accountDto.getPassword())
                .contact(Contact.builder().email(accountDto.getEmail()).phone(accountDto.getPhone()).build())
                .role(new Role(accountDto.getRoleId()))
                .build();
    }
}
