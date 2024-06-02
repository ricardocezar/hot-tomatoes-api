package com.example.hot_tomatoes_api.domain.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Producer {
    private String name;
    @Setter(AccessLevel.NONE)
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (this.movies == null) {
            this.movies = new ArrayList<>();
        }
        if (this.movies.contains(movie)) {
            return;
        }
        this.movies.add(movie);
    }

    public void addMovies(List<Movie> movies) {
        if (this.movies == null) {
            this.movies = new ArrayList<>();
        }
        this.movies.addAll(
                movies.stream()
                .filter(movie -> !this.movies.contains(movie))
                .toList());
    }

    public List<Movie> getAwardedMovies() {
        return movies.stream().filter(Movie::isAwardWinner).toList();
    }

    public Movie getFirstAwardedMovie() {
        return getAwardedMovies().stream()
                .min((Movie::compareTo))
                .orElse(null);
    }

    public Movie getLastAwardedMovie() {
        return getAwardedMovies().stream()
                .max((Movie::compareTo))
                .orElse(null);
    }

    /**
     * Calculates the interval between the first and last awarded movie of a producer.
     *
     * @return the interval in years between the first and last awarded movie as an Integer.
     */
    public Integer firstToLastAwardedMovieInterval() {
        if (movies.isEmpty()) {
            return null;
        }
        if (movies.size() == 1) {
            return 0;
        }
        return getLastAwardedMovie().getYear().value() - getFirstAwardedMovie().getYear().value();
    }
}
