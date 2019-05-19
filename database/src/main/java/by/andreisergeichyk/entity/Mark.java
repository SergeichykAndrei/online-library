package by.andreisergeichyk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mark_book", schema = "library_storage")
public class Mark extends BaseEntity<Long> {

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
