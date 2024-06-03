package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.domain.objects.PairOfMovies;

import java.util.Comparator;
import java.util.List;

public class MinimalIntervalBetweenAwardsCalculator implements AwardIntervalCalculator {
    private PairOfMovies minimumIntervalBetweenAwards(List<Movie> movies) {
        PairOfMovies pairOfMovies = null;
        if (movies.isEmpty()) {
            return null;
        }
        var awardedMovies = movies.stream().filter(Movie::isAwardWinner).toList();
        if (awardedMovies.size() == 1) {
            return null;
        }
        var orderedMovies = awardedMovies.stream()
                .sorted(Comparator.comparingInt(m -> m.getYear().value())).toList();
        int smallestInterval = 1000000;
        Movie movie1 = null;
        Movie movie2 = null;
        for (int i = 1; i < orderedMovies.size(); i++) {
            movie1 = orderedMovies.get(i - 1);
            movie2 = orderedMovies.get(i);
            int interval = movie2.getYear().value() - movie1.getYear().value();
            if (interval < smallestInterval) {
                smallestInterval = interval;
                pairOfMovies = new PairOfMovies(movie1, movie2);
            }
        }
        return pairOfMovies;
    }

    @Override
    public PairOfMovies calculate(List<Movie> movies) {
        return minimumIntervalBetweenAwards(movies);
    }
}
