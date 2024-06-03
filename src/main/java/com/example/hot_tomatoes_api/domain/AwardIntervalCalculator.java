package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.domain.objects.PairOfMovies;

import java.util.List;

@FunctionalInterface
public interface AwardIntervalCalculator {
    PairOfMovies calculate(List<Movie> movies);
}
