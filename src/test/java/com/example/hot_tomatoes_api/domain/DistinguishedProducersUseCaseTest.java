package com.example.hot_tomatoes_api.domain;

import com.example.hot_tomatoes_api.domain.distinguished_producers_usecase.DistinguishedProducersUseCase;
import com.example.hot_tomatoes_api.factory.MovieFactory;
import com.example.hot_tomatoes_api.repository.ProducerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Distinguished Producers UseCase")
@SpringBootTest
@ActiveProfiles("test")
class DistinguishedProducersUseCaseTest {
    @Nested
    @ExtendWith(MockitoExtension.class)
    @DisplayName("Scenarios with mocked data")
    class MockTests {
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

        @Test
        @DisplayName("should return empty map when there are no awarded producers")
        void whenThereAreNoAwardedProducers_thenReturnEmptyMap() {
            when(producerRepository.findAllAwardedProducers()).thenReturn(List.of());
            var result = distinguishedProducersUseCase.execute();
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("should return empty map when there are no distinguished producers")
        void whenThereAreNoDistinguishedProducers_thenReturnEmptyMap() {
            when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioNoMultiAwardedProducers());
            var result = distinguishedProducersUseCase.execute();
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("should return the same producer and interval for min and max when there are only one distinguished producer")
        void whenThereAreOnlyOneDistinguishedProducer_thenReturnTheSameProducer() {
            int expectedInterval = 3;
            when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioOneDistinguishedProducer());
            var result = distinguishedProducersUseCase.execute();
            assertEquals(1, result.get("min").size());
            assertEquals(1, result.get("max").size());
            assertEquals(expectedInterval, result.get("min").get(0).interval());
            assertEquals(expectedInterval, result.get("max").get(0).interval());
            assertThat(result.get("min").get(0)).isEqualTo(result.get("max").get(0));
        }

        @Test
        @DisplayName("should return the same producer but different interval for min and max when the only one producer has min and max interval")
        void whenTheSameProducersHasDifferentInterval_thenReturnTheSameProducer() {
            int expectedMinInterval = 1;
            int expectedMaxInterval = 8;
            when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioSameProducersWithDifferentInterval());
            var result = distinguishedProducersUseCase.execute();
            assertEquals(1, result.get("min").size());
            assertEquals(1, result.get("max").size());
            assertEquals(expectedMinInterval, result.get("min").get(0).interval());
            assertEquals(expectedMaxInterval, result.get("max").get(0).interval());
            assertThat(result.get("min").get(0).producerName()).isEqualTo(result.get("max").get(0).producerName());
            assertThat(result.get("min").get(0).interval()).isNotEqualTo(result.get("max").get(0).interval());
        }

        @Test
        @DisplayName("should return more than one producer in max when there are more than one distinguished producer")
        void whenThereAreMoreThanOneDistinguishedProducer_thenReturnMoreThanOneProducer() {
            when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioMoreThanOneDistinguishedProducer());
            var result = distinguishedProducersUseCase.execute();
            assertEquals(2, result.get("max").size());
        }
    }

    @Nested
    @Transactional
    @DisplayName("Scenarios with memory database data")
    class DatabaseTests {
        @Autowired
        DistinguishedProducersUseCase distinguishedProducersUseCase;
        @Autowired
        ProducerRepository producerRepository;

        @Test
        @DisplayName("should return one min and one max")
        void whenThereAreOneMinAndOneMax_thenReturnOneMinAndOneMax() {
            int expectedMinInterval = 1;
            int expectedMaxInterval = 13;
            var result = distinguishedProducersUseCase.execute();
            assertThat(result.get("min").get(0).producerName()).isEqualTo("Joel Silver");
            assertThat(result.get("min").get(0).interval()).isEqualTo(expectedMinInterval);
            assertThat(result.get("min").get(0).firstAwardedMovie().value()).isEqualTo(1990);
            assertThat(result.get("min").get(0).lastAwardedMovie().value()).isEqualTo(1991);
            assertThat(result.get("max").get(0).producerName()).isEqualTo("Matthew Vaughn");
            assertThat(result.get("max").get(0).interval()).isEqualTo(expectedMaxInterval);
            assertThat(result.get("max").get(0).firstAwardedMovie().value()).isEqualTo(2002);
            assertThat(result.get("max").get(0).lastAwardedMovie().value()).isEqualTo(2015);
        }
    }
}