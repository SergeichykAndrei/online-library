package by.andreisergeichyk.dto.accountbook;

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
public class AccountBookDto {

    private String bookName;
    private Long bookId;
    private String accountName;
    private String dateIssue;
    private String dateReturn;
}
