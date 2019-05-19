package by.andreisergeichyk.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book", schema = "library_storage")
public class Book extends BaseEntity<Long> {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "image")
    private String image;

    @Column(name = "number_copies")
    private Integer numberCopies;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "book")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "book")
    private List<Mark> marks = new ArrayList<>();

    @Version()
    @Column(name = "version")
    private Long version;

    public Book(String name, Genre genre, Author author, String image, Integer number_copies, String description) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.image = image;
        this.numberCopies = number_copies;
        this.description = description;
    }
}
