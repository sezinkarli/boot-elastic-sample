package main.service;

import main.dao.MovieRepository;
import main.model.Genre;
import main.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sezin karli
 * @since 2/28/15 1:56 PM
 *        User: Sezin Karli
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public List<Movie> getByName(String name) {
        return repository.findByName(name);
    }

    public List<Movie> getByRatingInterval(Double beginning, Double end) {
        return repository.findByRatingBetween(beginning, end);
    }

    public void addMovie(Movie movie) {
        repository.save(movie);
    }
}
