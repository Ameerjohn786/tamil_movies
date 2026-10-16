package com.movies.repository;

import com.movies.domain.Movies;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer> {

    List<Movies> findByMovieGenre(String genre);

    Optional<Movies> findByMovieName(String movieTitle);

    List<Movies> findByMovieActor(String actor, Pageable pageable);
}
