package by.andreisergeichyk.entity;

import by.andreisergeichyk.entity.BaseEntity;
import by.andreisergeichyk.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Searcher {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_NUMBER_BOOKS_ON_PAGE = 2;

    private String searchLine;
    private boolean searchMethod;
    private int currentPage;
    private int numberOfBooksOnPage;
    private List<Long> genresIds;

    public Searcher(List<Genre> genres) {
        this.genresIds = genres.stream().map(BaseEntity::getId).collect(Collectors.toList());
        this.currentPage = DEFAULT_PAGE;
        this.numberOfBooksOnPage = DEFAULT_NUMBER_BOOKS_ON_PAGE;
    }
}
