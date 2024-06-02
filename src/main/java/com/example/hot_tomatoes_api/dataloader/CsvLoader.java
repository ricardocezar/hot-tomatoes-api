package com.example.hot_tomatoes_api.dataloader;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class CsvLoader implements CommandLineRunner {
    Logger logger = Logger.getLogger(CsvLoader.class.getName());
    private final LoadDatabase loadDatabase;

    @Override
    public void run(String... args) throws Exception {
        logger.info("CSV Data load started");
        List<CsvBean> csvBeans = CsvFileReader.readCsvFile("data/movielist.csv");
        if (csvBeans.isEmpty()) {
            logger.info("CSV Data load ended with no data found");
            return;
        }
        Audit audit = loadDatabase.saveDataToDatabase(csvBeans);
        logger.info(audit.toString());
        logger.info("CSV Data load ended");
    }
}
