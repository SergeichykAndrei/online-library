package by.andreisergeichyk.dto.book;

import by.andreisergeichyk.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude = "comments")
@Builder
public class ViewFullInfoBookDto {

    private Long id;
    private String name;
    private String genre;
    private String author;
    private String image;
    private Integer numberCopies;
    private String description;
    private String dateIssue;
    private String dateReturn;
    private List<CommentDto> comments;
    private BigDecimal avgMark;
}
