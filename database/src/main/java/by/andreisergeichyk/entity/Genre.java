package by.andreisergeichyk.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genre", schema = "library_storage")
public class Genre extends BaseEntity<Long> {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
