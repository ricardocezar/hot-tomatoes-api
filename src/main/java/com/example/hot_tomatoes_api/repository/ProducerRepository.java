package com.example.hot_tomatoes_api.repository;

import com.example.hot_tomatoes_api.domain.objects.Producer;
import java.util.List;
import java.util.Set;

public interface ProducerRepository {
    Producer save(Producer producer);
    List<Producer> findAllAwardedProducers();
}
