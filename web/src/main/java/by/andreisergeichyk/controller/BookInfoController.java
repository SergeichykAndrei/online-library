package by.andreisergeichyk.controller;

import by.andreisergeichyk.converter.ModelToDtoConverter;
import by.andreisergeichyk.dto.comment.CommentRequestDto;
import by.andreisergeichyk.dto.book.ViewFullInfoBookDto;
import by.andreisergeichyk.dto.comment.CommentDto;
import by.andreisergeichyk.service.BookService;
import by.andreisergeichyk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookInfoController {

    private BookService bookService;
    private CommentService commentService;

    @Autowired
    public BookInfoController(CommentService commentService, BookService bookService) {
        this.bookService = bookService;
        this.commentService = commentService;
    }

    @GetMapping("/bookInfo")
    public String getBookInfo(Model model, Long bookId) {
        model.addAttribute("book", bookService.findById(bookId));

        return "book-info";
    }

    @ResponseBody
    @PostMapping(value = "/bookInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDto> getNewBookCommentsList(@RequestBody CommentRequestDto requestCommentDto) {
        commentService.save(requestCommentDto);
        ViewFullInfoBookDto book = bookService.findById(requestCommentDto.getBookId());

        return book.getComments();
    }
}
