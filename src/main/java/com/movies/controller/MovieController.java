package com.movies.controller;

import com.movies.domain.Movies;
import com.movies.exceptions.MovieAlreadyExist;
import com.movies.exceptions.MovieAppException;
import com.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {


    @Value("${message}")
    String msg;

    @Autowired
    MovieService service;

    @PostMapping("/movie")
    public ResponseEntity<Object> addMovie(@RequestBody Movies movies) throws MovieAlreadyExist {
        Movies Movie = service.createMovies(movies);
        return new ResponseEntity<Object>(Movie, HttpStatus.CREATED);
    }

    @GetMapping("/allMovies")
    public List<Movies> getMovies() {
        return service.getAllMovies();
    }

    @GetMapping("/movie/{movieId}")
    public Movies getMovie(@PathVariable("movieId") int movieId) throws MovieAppException {
        return service.getMovies(movieId);
    }

    @PutMapping("/movie")
    public String updateMovie(@RequestBody Movies movie) throws MovieAppException {
        Movies movies = service.getMovies(movie.getMovieId());
        if (movies != null) {
            service.updateMovie(movie);
        }
        return movie.getMovieName() + " Movie updated";
    }

    @DeleteMapping("/movie/{id}")
    public String deleteMovie(@PathVariable("id") int movieId) {
        String name = service.deleteMovie(movieId);
        return name + " Movie deleted";
    }

    @GetMapping("movieGenre/{genre}")
    public List<Movies> getMovieGenre(@PathVariable("genre") String genre) {
        return service.getMovieGenre(genre);
    }

    @GetMapping("/movieList")
    public Page<Movies> getMovies(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return service.getMovieList(pageable);
    }

    @GetMapping("/movieGenreList")
    public List<Movies> getMoviesGenre(@RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return service.getMovieGenreList(pageable);
    }

    @GetMapping("/movieCollections")
    public List<Movies> sortMovies(@RequestParam int page, @RequestParam int pageSize, String movieName) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(movieName));
        return service.getSortedMovies(pageable);
    }

    @GetMapping("/actorMovies")
    public List<Movies> getActorMovies(@RequestParam String actor, @RequestParam int page, @RequestParam int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "movieCollections"));
        return service.getMovieByActor(actor, pageable);
    }

    @GetMapping("/actorsMoviesList")
    public Map<String, Long> getActorsMoviesList() {
        return service.actorsMoviesList();
    }

    @GetMapping("/highestCollections")
    public Movies getHighestCollections() {
        return service.getHighestCollections();
    }

    @GetMapping("/movieReleased")
    public List<Movies> releasedYear() {
        return service.movieReleasedYear();
    }
}
