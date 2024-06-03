package com.example.hot_tomatoes_api.domain.objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class Movie {
    private Year year;
    private String title;
    @Setter(AccessLevel.NONE)
    private Set<Producer> producers = new HashSet<>();
    private boolean awardWinner = false;

    public Movie(int year, String title, String producer, boolean awardWinner) {
        this.year = new Year(year);
        this.title = title;
        this.awardWinner = awardWinner;
        addProducer(new Producer(producer));
    }

    public Movie(int year, String title, boolean awardWinner) {
        this.year = new Year(year);
        this.title = title;
        this.awardWinner = awardWinner;
    }

    public void addProducer(Producer producer) {
        if (this.producers == null) {
            this.producers = new HashSet<>();
        }
        if (this.producers.contains(producer)) {
            return;
        }
        this.producers.add(producer);
        if (producer.getMovies().contains(this)) {
            return;
        }
        producer.addMovie(this);
    }

    public void addProducers(Set<Producer> producers) {
        if (this.producers == null) {
            this.producers = new HashSet<>();
        }
        this.producers.addAll(
                producers.stream()
                        .filter(producer -> !this.producers.contains(producer))
                        .toList());
        producers.forEach(p -> { if (p.getMovies().contains(this)) return; p.addMovie(this); });
    }

    public int compareTo(Movie movie) {
        return this.getYear().compareTo(movie.getYear());
    }
}
