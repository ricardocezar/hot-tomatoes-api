package com.example.hot_tomatoes_api.domain.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class Producer {
    private String name;
    @Setter(AccessLevel.NONE)
    private List<Movie> movies = new ArrayList<>();
    private Movie first = null;
    private Movie second = null;

    public Producer(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public Producer addMovie(Movie movie) {
        if (this.movies == null) {
            this.movies = new ArrayList<>();
        }
        if (this.movies.contains(movie)) {
            return this;
        }
        this.movies.add(movie);
        return this;
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

    public Optional<PairOfMovies> getClosestAwardedMovies() {
        minimalIntervalBetweenAwards();
        if (first == null || second == null) {
            return Optional.empty();
        }
        return Optional.of(new PairOfMovies(first, second));
    }

    public Integer minimalIntervalBetweenAwards() {
        if (movies.isEmpty() || getAwardedMovies().isEmpty()) {
            return null;
        }
        if (getAwardedMovies().size() == 1) {
            return 0;
        }
        var orderedMovies = getAwardedMovies().stream()
                .sorted(Comparator.comparingInt(m -> m.getYear().value())).toList();
        int smallestInterval = 1000000;
        for (int i = 1; i < orderedMovies.size(); i++) {
            Movie movie1 = orderedMovies.get(i - 1);
            Movie movie2 = orderedMovies.get(i);
            int interval = movie2.getYear().value() - movie1.getYear().value();
            if (interval < smallestInterval) {
                smallestInterval = interval;
                this.first = movie1;
                this.second = movie2;
            }
        }
        return smallestInterval;
    }
}
