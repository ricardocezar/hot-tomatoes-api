package com.example.hot_tomatoes_api.domain.objects;

import lombok.Getter;

@Getter
public class PairOfMovies {
    private Movie first;
    private Movie last;

    public PairOfMovies(Movie first, Movie last) {
        this.first = first;
        this.last = last;
    }

    public int getInterval() {
        return last.getYear().value() - first.getYear().value();
    }
}
