package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.factory.MovieFactory;
import com.example.hot_tomatoes_api.repository.ProducerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DistinguishedProducersUseCase.class, FastestWinnerProducersFinder.class, SlowestWinnerProducersFinder.class})
@ExtendWith(MockitoExtension.class)
@DisplayName("Distinguished Producers UseCase")
class DistinguishedProducersUseCaseTest {
    @Autowired
    DistinguishedProducersUseCase distinguishedProducersUseCase;
    @MockBean
    ProducerRepository producerRepository;

    @Test
    @DisplayName("should return one min and one max when there are one min and one max producers")
    void whenThereAreOneMinAndOneMax_thenReturnOneMinAndOneMax() {
        int expectedMinInterval = 1;
        int expectedMaxInterval = 11;
        when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioOneMinAndOneMax());
        var result = distinguishedProducersUseCase.execute();
        assertEquals(1, result.get("min").size());
        assertEquals(1, result.get("max").size());
        assertEquals(expectedMinInterval, result.get("min").get(0).interval());
        assertEquals(expectedMaxInterval, result.get("max").get(0).interval());
    }
}