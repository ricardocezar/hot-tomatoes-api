package com.example.hot_tomatoes_api.domain.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Producer {
    private String name;
    @Setter(AccessLevel.NONE)
    private List<Movie> movies = new ArrayList<>();

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
}
