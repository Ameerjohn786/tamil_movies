package com.movies.repository;

import com.movies.domain.Actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actors, Integer> {

}
