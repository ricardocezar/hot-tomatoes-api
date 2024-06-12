package com.example.hot_tomatoes_api.repository;

import com.example.hot_tomatoes_api.domain.objects.Producer;
import java.util.List;

public interface ProducerRepository {
    Producer save(Producer producer);
    List<Producer> findAllAwardedProducers();
}
