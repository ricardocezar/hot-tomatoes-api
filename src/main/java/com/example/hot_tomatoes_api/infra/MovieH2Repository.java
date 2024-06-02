package com.example.hot_tomatoes_api.infra;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.infra.dataaccess.AwardDao;
import com.example.hot_tomatoes_api.infra.dataaccess.MovieDao;
import com.example.hot_tomatoes_api.infra.dataaccess.ProducerDao;
import com.example.hot_tomatoes_api.infra.entity.AwardEntity;
import com.example.hot_tomatoes_api.infra.entity.MovieEntity;
import com.example.hot_tomatoes_api.infra.entity.ProducerEntity;
import com.example.hot_tomatoes_api.infra.mapper.MovieMapper;
import com.example.hot_tomatoes_api.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MovieH2Repository implements MovieRepository {
    private final MovieDao movieDao;
    private final ProducerDao producerDao;
    private final AwardDao awardDao;

    @Override
    @Transactional
    public Movie save(Movie movie) {
        MovieEntity newMovieEntity = MovieMapper.mapDomainObjectToEntity(movie);
        Set<ProducerEntity> savedProducers = new HashSet<>();
        for (ProducerEntity producerEntity : newMovieEntity.getProducers()) {
            Optional<ProducerEntity> existentProducer = producerDao.findByName(producerEntity.getName());
            if (existentProducer.isPresent()) {
                savedProducers.add(existentProducer.get());
                continue;
            }
            savedProducers.add(producerDao.save(producerEntity));
        }
        newMovieEntity.setProducers(savedProducers);
        newMovieEntity = movieDao.save(newMovieEntity);
        if (movie.isAwardWinner()) {
            newMovieEntity.setAward(
                    awardDao.save(new AwardEntity(newMovieEntity.getReleaseYear(), newMovieEntity)));
        }
        return MovieMapper.mapEntityToDomainObject(newMovieEntity);
    }
}
