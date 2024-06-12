package com.example.hot_tomatoes_api.domain.distinguished_producers_usecase;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import lombok.Getter;

@Getter
public class PairOfMovies {
    private final String producerName;
    private final Movie first;
    private final Movie last;
    private final int interval;

    public PairOfMovies(String producerName, Movie first, Movie last) {
        this.producerName = producerName;
        this.first = first;
        this.last = last;
        this.interval = last.getYear().value() - first.getYear().value();
    }
}
