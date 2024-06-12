package com.example.hot_tomatoes_api.dataloader;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.domain.objects.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class MovieDataTransformer {
    private final ProducerDataTransformer producerDataTransformer;

    public Movie movieTransformer(CsvBean csvBean) {
        Movie movie = new Movie();
        movie.setYear(new Year(Integer.parseInt(csvBean.getYear())));
        movie.setTitle(csvBean.getTitle());
        movie.addProducers(producerDataTransformer.producerTransformer(csvBean.getProducers()));
        movie.setAwardWinner(Objects.equals("yes", csvBean.getWinner().trim()));
        return movie;
    }
}
