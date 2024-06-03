package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.domain.objects.DistinguishedProducer;
import com.example.hot_tomatoes_api.domain.objects.Producer;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class SlowestWinnerProducersFinder {
    public List<DistinguishedProducer> execute(List<Producer> awardedProducers) {
        List<Producer> multiAwardedProducers = awardedProducers.stream()
                .filter(p -> p.getAwardedMovies().size() > 1)
                .toList();
        if (multiAwardedProducers.isEmpty()) {
            return List.of();
        }
        if (multiAwardedProducers.size() == 1) {
            var pair = multiAwardedProducers.get(0).getFarthestAwardedMovies().get();
            return List.of(new DistinguishedProducer(
                    multiAwardedProducers.get(0).getName(),
                    pair.getInterval(),
                    pair.getFirst().getYear(),
                    pair.getLast().getYear()));
        }
        Producer slowestWinner = multiAwardedProducers.stream()
                .max(Comparator.comparingInt(Producer::minimalIntervalBetweenAwards)).get();
        return multiAwardedProducers.stream()
                .filter(p -> Objects.equals(p.minimalIntervalBetweenAwards(), slowestWinner.minimalIntervalBetweenAwards()))
                .map(p -> new DistinguishedProducer(
                        p.getName(),
                        p.minimalIntervalBetweenAwards(),
                        p.getClosestAwardedMovies().get().getFirst().getYear(),
                        p.getClosestAwardedMovies().get().getLast().getYear()))
                .toList();
    }
}
