package com.example.hot_tomatoes_api.factory;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.domain.objects.Producer;
import java.util.List;
import java.util.UUID;

public class MovieFactory {

    /**
     * @return a list of two producers:
     * John Mark, producing 3 movies: 1999, 2000, 2003
     * Mary McPerson, producing 2 movies: 1990, 2001
     */
    public static List<Producer> scenarioOneMinAndOneMax() {
        Producer johnProducer = new Producer("John Mark");
        johnProducer
                .addMovie(new Movie(1999, randomTitle(), true))
                .addMovie(new Movie(2000, randomTitle(),  true))
                .addMovie(new Movie(2000, randomTitle(),  false))
                .addMovie(new Movie(2003, randomTitle(),  true));
        Producer maryProducer = new Producer("Mary McPerson");
        maryProducer
                .addMovie(new Movie(1990, randomTitle(), true))
                .addMovie(new Movie(2001, randomTitle(),  true));
        return List.of(johnProducer, maryProducer);
    }

    public static List<Producer> scenarioNoMultiAwardedProducers() {
        Producer johnProducer = new Producer("John Mark");
        johnProducer
                .addMovie(new Movie(1999, randomTitle(), true))
                .addMovie(new Movie(2000, randomTitle(),  false))
                .addMovie(new Movie(2003, randomTitle(),  false));
        Producer maryProducer = new Producer("Mary McPerson");
        maryProducer
                .addMovie(new Movie(1990, randomTitle(), true))
                .addMovie(new Movie(2001, randomTitle(),  false));
        return List.of(johnProducer, maryProducer);
    }

    private static String randomTitle() {
        return UUID.randomUUID().toString();
    }

    public static List<Producer> scenarioOneDistinguishedProducer() {
        Producer johnProducer = new Producer("John Mark");
        johnProducer
                .addMovie(new Movie(1999, randomTitle(), false))
                .addMovie(new Movie(2000, randomTitle(),  true))
                .addMovie(new Movie(2003, randomTitle(),  true));
        Producer maryProducer = new Producer("Mary McPerson");
        maryProducer
                .addMovie(new Movie(1990, randomTitle(), false))
                .addMovie(new Movie(2001, randomTitle(),  false));
        return List.of(johnProducer, maryProducer);
    }

    public static List<Producer> scenarioSameProducersWithDifferentInterval() {
        Producer johnProducer = new Producer("John Mark");
        johnProducer
                .addMovie(new Movie(1999, randomTitle(), true))
                .addMovie(new Movie(2000, randomTitle(),  true))
                .addMovie(new Movie(2003, randomTitle(),  false))
                .addMovie(new Movie(2008, randomTitle(),  true));
        Producer maryProducer = new Producer("Mary McPerson");
        maryProducer
                .addMovie(new Movie(1990, randomTitle(), false))
                .addMovie(new Movie(1995, randomTitle(),  false));
        return List.of(johnProducer, maryProducer);
    }
}
