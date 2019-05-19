package by.andreisergeichyk.service;

import by.andreisergeichyk.entity.Mark;
import by.andreisergeichyk.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> findAllByBookId(Serializable bookId) {
        return markRepository.findAllByBookId(bookId);
    }

    public BigDecimal avgVote(Long bookId) {
        return markRepository.avgMark(bookId);
    }

    public Mark save(Mark object) {
        return markRepository.save(object);
    }

    public void delete(Mark object) {
        markRepository.delete(object);
    }

    public Mark findById(Long id) {
        return markRepository.findById(id).orElse(null);
    }

    public List<Mark> findAll() {
        Iterable<Mark> result = markRepository.findAll();
        ArrayList<Mark> votes = new ArrayList<>();
        result.forEach(votes::add);

        return votes;
    }
}
