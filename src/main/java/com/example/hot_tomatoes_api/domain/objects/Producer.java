package com.example.hot_tomatoes_api.domain.objects;

import com.example.hot_tomatoes_api.domain.MaximumIntervalBetweenAwardsCalculator;
import com.example.hot_tomatoes_api.domain.MinimalIntervalBetweenAwardsCalculator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class Producer {
    private String name;
    @Setter(AccessLevel.NONE)
    private List<Movie> movies = new ArrayList<>();
    private PairOfMovies closest;
    private PairOfMovies farthest;

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
        return Optional.ofNullable(closest);
    }

    public Integer minimalIntervalBetweenAwards() {
        var minimalIntervalCalculator = new MinimalIntervalBetweenAwardsCalculator();
        closest = minimalIntervalCalculator.calculate(getAwardedMovies());
        if (closest == null) {
            return null;
        }
        return closest.getInterval();
    }

    public Optional<PairOfMovies> getFarthestAwardedMovies() {
        maximumIntervalBetweenAwards();
        return Optional.of(farthest);
    }

    public Integer maximumIntervalBetweenAwards() {
        var maximumIntervalCalculator = new MaximumIntervalBetweenAwardsCalculator();
        farthest = maximumIntervalCalculator.calculate(getAwardedMovies());
        if (farthest == null) {
            return null;
        }
        return farthest.getInterval();
    }
}
