package com.example.hot_tomatoes_api.api;

import com.example.hot_tomatoes_api.domain.distinguished_producers_usecase.DistinguishedProducer;

import java.util.List;
import java.util.Map;

public class DistinguishedProducerMapper {
    public static DistinguishedProducersDto mapDomainObjectToDto(Map<String, List<DistinguishedProducer>> distinguishedProducers) {
        DistinguishedProducersDto dto = new DistinguishedProducersDto();
        if (distinguishedProducers.isEmpty()) {
            return dto;
        }
        var min = distinguishedProducers.get("min");
        var max = distinguishedProducers.get("max");
        for (DistinguishedProducer fastestWinner : min) {
            DistinguishedProducerDto f = new DistinguishedProducerDto(
                    fastestWinner.producerName(),
                    fastestWinner.interval(),
                    fastestWinner.firstAwardedMovie().value(),
                    fastestWinner.lastAwardedMovie().value());
            dto.getMin().add(f);
        }
        for (DistinguishedProducer fastestWinner : max) {
            DistinguishedProducerDto s = new DistinguishedProducerDto(
                    fastestWinner.producerName(),
                    fastestWinner.interval(),
                    fastestWinner.firstAwardedMovie().value(),
                    fastestWinner.lastAwardedMovie().value());
            dto.getMax().add(s);
        }
        return dto;
    }
}
