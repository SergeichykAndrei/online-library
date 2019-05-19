package by.andreisergeichyk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@Entity
@Table(name = "account_book", schema = "library_storage")
public class AccountBook extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "date_issue")
    private LocalDate dateIssue;

    @Column(name = "date_return")
    private LocalDate dateReturn;
}
