package com.example.hot_tomatoes_api.infra.mapper;

import com.example.hot_tomatoes_api.domain.objects.Producer;
import com.example.hot_tomatoes_api.infra.entity.ProducerEntity;
import java.util.Set;
import java.util.stream.Collectors;

public class ProducerMapper {

    public static ProducerEntity mapDomainObjectToEntity(Producer domainProducer) {
        ProducerEntity producerEntity = new ProducerEntity();
        producerEntity.setName(domainProducer.getName());
        return producerEntity;
    }

    public static Set<ProducerEntity> mapDomainObjectToEntity(Set<Producer> domainProducers) {
        return domainProducers.stream()
                .map(ProducerMapper::mapDomainObjectToEntity)
                .collect(Collectors.toSet());
    }

    public static Producer mapEntityToDomainObject(ProducerEntity producerEntity) {
        Producer producer = new Producer();
        producer.setName(producerEntity.getName());
        return producer;
    }

    public static Set<Producer> mapEntityToDomainObject(Set<ProducerEntity> producerEntity) {
        return producerEntity.stream()
                .map(ProducerMapper::mapEntityToDomainObject)
                .collect(Collectors.toSet());
    }
}
