package com.example.hot_tomatoes_api.dataloader;

import com.example.hot_tomatoes_api.domain.objects.Movie;
import com.example.hot_tomatoes_api.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class LoadDatabase {
    private final Logger logger = Logger.getLogger(LoadDatabase.class.getName());
    private final MovieDataTransformer movieDataTransformer;
    private final MovieRepository movieRepository;

    public Audit saveDataToDatabase(List<CsvBean> csvBeans) {
        Audit audit = new Audit(csvBeans.size());
        for(CsvBean csvBean: csvBeans) {
            Movie newMovie = movieDataTransformer.movieTransformer(csvBean);
            try {
                movieRepository.save(newMovie);
                audit.addSuccessfulLine();
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                audit.addFailedLine(csvBean.toString(), e.getMessage());
            }
        }
        return audit;
    }
}
