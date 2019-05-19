package by.andreisergeichyk.dto.book;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ViewMainInfoBookDto {

    private Long id;
    private String name;
    private String genre;
    private String author;
    private String image;
    private BigDecimal avgMark;
    private Integer numberCopies;
}
