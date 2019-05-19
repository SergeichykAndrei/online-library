package by.andreisergeichyk.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInternalizationFieldDto {

    private String bookMark;
    private String bookGenre;
    private String numberCopiesBook;
    private String bookAuthor;
}
