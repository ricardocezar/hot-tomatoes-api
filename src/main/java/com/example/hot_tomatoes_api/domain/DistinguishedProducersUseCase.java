package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.domain.objects.DistinguishedProducer;
import com.example.hot_tomatoes_api.domain.objects.Producer;
import com.example.hot_tomatoes_api.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DistinguishedProducersUseCase {
    private final FastestWinnerProducersFinder fastestWinnerProducersFinder;
    private final SlowestWinnerProducersFinder slowestWinnerProducersFinder;
    private final ProducerRepository producerRepository;

    public Map<String, List<DistinguishedProducer>> execute() {
        List<Producer> awardedProducers = producerRepository.findAllAwardedProducers();
        if (awardedProducers.isEmpty()) {
            return Map.of();
        }
        List<DistinguishedProducer> fastestWinners = fastestWinnerProducersFinder.execute(awardedProducers);
        List<DistinguishedProducer> slowestWinners = slowestWinnerProducersFinder.execute(awardedProducers);
        if (fastestWinners.isEmpty() && slowestWinners.isEmpty()) {
            return Map.of();
        }
        return Map.of(
                "min", fastestWinners,
                "max", slowestWinners
        );
    }
}
