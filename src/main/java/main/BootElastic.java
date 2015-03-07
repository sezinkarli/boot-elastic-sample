package main;

import main.model.Genre;
import main.model.Movie;
import main.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sezin karli
 * @since 2/28/15 1:56 PM
 *        User: Sezin Karli
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BootElastic implements CommandLineRunner {

    @Autowired
    private MovieService movieService;

    private static final Logger logger = LoggerFactory.getLogger(BootElastic.class);

    private void addSomeMovies() {
        Movie starWars = getFirstMovie();
        movieService.addMovie(starWars);

        Movie princessBride = getSecondMovie();
        movieService.addMovie(princessBride);
    }

    private Movie getSecondMovie() {
        Movie secondMovie = new Movie();
        secondMovie.setId("2");
        secondMovie.setRating(8.4d);
        secondMovie.setName("The Princess Bride");

        List<Genre> princessPrideGenre = new ArrayList<Genre>();
        princessPrideGenre.add(new Genre("ACTION"));
        princessPrideGenre.add(new Genre("ROMANCE"));
        secondMovie.setGenre(princessPrideGenre);

        return secondMovie;
    }

    private Movie getFirstMovie() {
        Movie firstMovie = new Movie();
        firstMovie.setId("1");
        firstMovie.setRating(9.6d);
        firstMovie.setName("Star Wars");

        List<Genre> starWarsGenre = new ArrayList<Genre>();
        starWarsGenre.add(new Genre("ACTION"));
        starWarsGenre.add(new Genre("SCI_FI"));
        firstMovie.setGenre(starWarsGenre);

        return firstMovie;
    }

    public void run(String... args) throws Exception {
        addSomeMovies();
        // We indexed star wars and pricess bride to our movie
        // listing in elastic search

        List<Movie> starWarsNameQuery = movieService.getByName("Star Wars");
        logger.info("Content of star wars name query is {}", starWarsNameQuery);

        List<Movie> brideQuery = movieService.getByName("The Princess Bride");
        logger.info("Content of princess bride name query is {}", brideQuery);


        List<Movie> byRatingInterval = movieService.getByRatingInterval(6d, 9d);
        logger.info("Content of Rating Interval query is {}", byRatingInterval);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootElastic.class, args);
    }
}
