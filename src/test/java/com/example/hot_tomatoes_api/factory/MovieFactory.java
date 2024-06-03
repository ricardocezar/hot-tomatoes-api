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

    private static String randomTitle() {
        return UUID.randomUUID().toString();
    }
}
