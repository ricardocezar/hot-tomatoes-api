package com.example.hot_tomatoes_api.dataloader;

import com.example.hot_tomatoes_api.factory.CsvBeanFactory;
import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {MovieDataTransformer.class, ProducerDataTransformer.class, LoadDatabase.class})
@ExtendWith(MockitoExtension.class)
@DisplayName("Data loader tests")
class LoadDatabaseTest {
    @MockBean
    MovieRepository movieRepository;
    @Autowired
    private LoadDatabase loadDatabase;
    @Captor
    ArgumentCaptor<Movie> movieCaptor;

    @Test
    @DisplayName("Should save the same number of movies, with the same title and winner as received in the CSV file")
    void saveDataToDatabase_success() {
        int validMoviesCount = 2;
        List<CsvBean> csvBeans = CsvBeanFactory.getValidCsvBeans(validMoviesCount);
        loadDatabase.saveDataToDatabase(csvBeans);
        verify(movieRepository, times(validMoviesCount)).save(movieCaptor.capture());
        List<Movie> savedMovies = movieCaptor.getAllValues();
        assertThat(savedMovies).hasSameSizeAs(csvBeans);
        assertThat(savedMovies.stream().map(Movie::getTitle).toList())
                .containsExactlyElementsOf(csvBeans.stream().map(CsvBean::getTitle).toList());
        assertThat(savedMovies.stream().filter(Movie::isAwardWinner).toList())
                .hasSameSizeAs(csvBeans.stream().filter(csvBean -> "yes".equals(csvBean.getWinner())).toList());
    }
}