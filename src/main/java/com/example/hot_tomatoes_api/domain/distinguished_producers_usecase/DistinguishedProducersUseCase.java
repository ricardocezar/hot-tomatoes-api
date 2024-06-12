package com.example.hot_tomatoes_api.domain.distinguished_producers_usecase;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.domain.objects.Producer;
import com.example.hot_tomatoes_api.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class DistinguishedProducersUseCase {
    private final ProducerRepository producerRepository;

    public Map<String, List<DistinguishedProducer>> execute() {
        List<PairOfMovies> closestAwardedMovies;
        List<PairOfMovies> farthestAwardedMovies;
        List<Producer> awardedProducers = producerRepository.findAllAwardedProducers();
        if (awardedProducers.isEmpty()) {
            return Map.of();
        }
        TreeMap<Integer, List<PairOfMovies>> pairsOfConsecutiveMoviesGroupByInterval = new TreeMap<>();
        for (Producer producer : awardedProducers) {
            List<PairOfMovies> pairsOfConsecutiveMovies = getPairsOfConsecutiveAwardedMovies(producer);
            if (pairsOfConsecutiveMovies.isEmpty()) {
                continue;
            }
            for (PairOfMovies pairOfMovies : pairsOfConsecutiveMovies) {
                if (!pairsOfConsecutiveMoviesGroupByInterval.containsKey(pairOfMovies.getInterval())) {
                    pairsOfConsecutiveMoviesGroupByInterval.put(pairOfMovies.getInterval(), new ArrayList<>());
                }
                pairsOfConsecutiveMoviesGroupByInterval.get(pairOfMovies.getInterval()).add(pairOfMovies);
            }
        }
        if (pairsOfConsecutiveMoviesGroupByInterval.isEmpty()) {
            return Map.of();
        }
        closestAwardedMovies = pairsOfConsecutiveMoviesGroupByInterval.get(pairsOfConsecutiveMoviesGroupByInterval.firstKey());
        farthestAwardedMovies = pairsOfConsecutiveMoviesGroupByInterval.get(pairsOfConsecutiveMoviesGroupByInterval.lastKey());
        return Map.of(
                "min", mapToDistinguishedProducers(closestAwardedMovies),
                "max", mapToDistinguishedProducers(farthestAwardedMovies)
        );
    }

    private List<PairOfMovies> getPairsOfConsecutiveAwardedMovies(Producer producer) {
        if (producer.getAwardedMovies().size() < 2) {
            return List.of();
        }
        List<Movie> awardedMovies = producer.getAwardedMovies().stream().sorted(Comparator.comparingInt(m -> m.getYear().value())).toList();
        return IntStream
                .range(0, awardedMovies.size() - 1)
                .mapToObj(i -> new PairOfMovies(producer.getName(), awardedMovies.get(i), awardedMovies.get(i + 1)))
                .sorted(Comparator.comparing(PairOfMovies::getInterval))
                .toList();
    }

    private List<DistinguishedProducer> mapToDistinguishedProducers(List<PairOfMovies> pairs) {
        return pairs.stream()
                .sorted(Comparator.comparing(PairOfMovies::getProducerName))
                .map(p -> new DistinguishedProducer(
                        p.getProducerName(),
                        p.getInterval(),
                        p.getFirst().getYear(),
                        p.getLast().getYear()))
                .collect(Collectors.toList());
    }
}
