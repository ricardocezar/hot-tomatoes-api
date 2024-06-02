package com.example.hot_tomatoes_api.infra.mapper;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.domain.objects.Year;
import com.example.hot_tomatoes_api.infra.entity.MovieEntity;

import java.util.List;

public class MovieMapper {

    public static MovieEntity mapDomainObjectToEntity(Movie domainMovie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setReleaseYear(domainMovie.getYear().value());
        movieEntity.setTitle(domainMovie.getTitle());
        movieEntity.setProducers(ProducerMapper.mapDomainObjectToEntity(domainMovie.getProducers()));
        return movieEntity;
    }

    public static Movie mapEntityToDomainObject(MovieEntity movieEntity) {
        Movie movie = new Movie();
        movie.setYear(new Year(movieEntity.getReleaseYear()));
        movie.setTitle(movieEntity.getTitle());
        movie.addProducers(ProducerMapper.mapEntityToDomainObject(movieEntity.getProducers()));
        if (movieEntity.getAward() != null) {
            movie.setAwardWinner(true);
        }
        return movie;
    }

    public static List<Movie> mapEntityToDomainObject(List<MovieEntity> movieEntities) {
        return movieEntities.stream().map(MovieMapper::mapEntityToDomainObject).toList();
    }
}
