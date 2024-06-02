package com.example.hot_tomatoes_api.infra;

import com.example.hot_tomatoes_api.domain.objects.Producer;
import com.example.hot_tomatoes_api.infra.dataaccess.ProducerDao;
import com.example.hot_tomatoes_api.infra.entity.ProducerEntity;
import com.example.hot_tomatoes_api.infra.mapper.MovieMapper;
import com.example.hot_tomatoes_api.infra.mapper.ProducerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProducerRepository implements com.example.hot_tomatoes_api.repository.ProducerRepository {
    private final ProducerDao producerDao;

    @Override
    @Transactional
    public Producer save(Producer producer) {
        Optional<ProducerEntity> existingProducer = producerDao.findByName(producer.getName());
        if (existingProducer.isPresent()) {
            return ProducerMapper.mapEntityToDomainObject(existingProducer.get());
        }
        ProducerEntity producerEntity = ProducerMapper.mapDomainObjectToEntity(producer);
        return ProducerMapper.mapEntityToDomainObject(producerDao.save(producerEntity));
    }

    @Override
    public List<Producer> findAllAwardedProducers() {
        List<Producer> awardedProducers = new ArrayList<>();
        Set<ProducerEntity> producerEntities = producerDao.findAllByMoviesAwardIsNotNull();
        for(ProducerEntity producer : producerEntities) {
            Producer awardedProducer = ProducerMapper.mapEntityToDomainObject(producer);
            awardedProducer.addMovies(MovieMapper.mapEntityToDomainObject(producer.getMovies()));
            awardedProducers.add(awardedProducer);
        }
        return awardedProducers;
    }
}
