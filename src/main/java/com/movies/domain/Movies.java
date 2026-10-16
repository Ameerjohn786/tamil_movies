package com.movies.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    @Id
    private int movieId;
    private String movieName;
    private int releasedYear;
    private double movieRating;
    private long movieCollections;
    private String movieGenre;
    private String movieActor;
}
