package com.example.hot_tomatoes_api.repository;

import com.example.hot_tomatoes_api.domain.objects.Movie;

public interface MovieRepository {
    Movie save(Movie movie);
}
