package com.example.hot_tomatoes_api.infra.dataaccess;

import com.example.hot_tomatoes_api.infra.entity.ProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Set;

public interface ProducerDao extends JpaRepository<ProducerEntity, Long> {
    Optional<ProducerEntity> findByName(String name);
    Set<ProducerEntity> findAllByMoviesAwardIsNotNull();
}
