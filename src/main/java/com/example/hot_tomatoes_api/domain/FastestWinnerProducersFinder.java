package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.domain.objects.DistinguishedProducer;
import com.example.hot_tomatoes_api.domain.objects.Producer;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class FastestWinnerProducersFinder {
    public List<DistinguishedProducer> execute(List<Producer> awardedProducers) {
        List<Producer> multiAwardedProducers = awardedProducers.stream()
                .filter(p -> p.getAwardedMovies().size() > 1)
                .toList();
        if (multiAwardedProducers.isEmpty()) {
            return List.of();
        }
        Producer fastestWinner = multiAwardedProducers.stream()
                .min(Comparator.comparingInt(Producer::minimalIntervalBetweenAwards)).get();
        return multiAwardedProducers.stream()
                .filter(p -> Objects.equals(p.minimalIntervalBetweenAwards(), fastestWinner.minimalIntervalBetweenAwards()))
                .map(p -> new DistinguishedProducer(
                        p.getName(),
                        p.minimalIntervalBetweenAwards(),
                        p.getClosestAwardedMovies().get().getFirst().getYear(),
                        p.getClosestAwardedMovies().get().getLast().getYear()))
                .toList();
    }
}
