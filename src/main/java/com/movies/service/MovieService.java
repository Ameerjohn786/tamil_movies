package com.movies.service;

import com.movies.domain.Movies;
import com.movies.exceptions.MovieAlreadyExist;
import com.movies.exceptions.MovieAppException;
import com.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    public Movies getMovies(int movieId) throws MovieAppException {
        return movieRepo.findById(movieId).orElseThrow(() -> new MovieAppException(""));
    }

    @CacheEvict(value = "movies", allEntries = true)
    public Movies createMovies(Movies movies) throws MovieAlreadyExist {
        Optional<Movies> movie = movieRepo.findByMovieName(movies.getMovieName());
        if (movie.isPresent()) {
            throw new MovieAlreadyExist("Already Exist");
        }
        return movieRepo.save(movies);
    }

    @Cacheable("movies")
    public List<Movies> getAllMovies() {
        return movieRepo.findAll();
    }

    public List<Movies> getMovieGenre(String genre) {
        return movieRepo.findByMovieGenre(genre);
    }

    public Page<Movies> getMovieList(Pageable pageable) {
        return movieRepo.findAll(pageable);
    }

    public List<Movies> getMovieGenreList(Pageable pageable) {
        return movieRepo.findAll(pageable).getContent();
    }

    public List<Movies> getSortedMovies(Pageable pageable) {
        return movieRepo.findAll(pageable).getContent();
    }

    public List<Movies> getMovieByActor(String actor, Pageable pageable) {
        return movieRepo.findByMovieActor(actor, pageable);
    }

    public String deleteMovie(int movieId) {
        Movies movie = movieRepo.findById(movieId).get();
        movieRepo.delete(movie);
        return movie.getMovieName();
    }

    public Movies updateMovie(Movies movies) {
       return movieRepo.save(movies);

    }

    public Map<String, Long> actorsMoviesList() {
        List<Movies> getMovies = movieRepo.findAll();
        return getMovies.stream().collect(Collectors.groupingBy(Movies::getMovieActor, Collectors.counting()));
    }

    public Movies getHighestCollections() {
        List<Movies> getMovies = movieRepo.findAll();
        return getMovies.stream().max(Comparator.comparing(Movies::getMovieCollections)).get();
    }

    public List<Movies> movieReleasedYear() {
        return movieRepo.findAll().stream().sorted(Comparator.comparing(Movies::getReleasedYear).thenComparing(Movies::getMovieRating)).toList();
    }

}
