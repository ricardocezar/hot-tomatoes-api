package com.example.hot_tomatoes_api.dataloader;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvFileReader {
    private static final Logger logger = Logger.getLogger(CsvFileReader.class.getName());

    public static List<CsvBean> readCsvFile(String filePath) {
        try (InputStream is = CsvFileReader.class.getClassLoader().getResourceAsStream(filePath)) {
            Reader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CsvToBeanBuilder<CsvBean> beanBuilder = new CsvToBeanBuilder<>(reader);
            beanBuilder.withType(CsvBean.class);
            beanBuilder.withSeparator(';');
            beanBuilder.withIgnoreLeadingWhiteSpace(true);
            beanBuilder.withIgnoreEmptyLine(true);
            return beanBuilder.build().parse();
        } catch (NullPointerException | IOException e) {
            logger.severe(String.format("Error opening csv file `%s`", filePath));
            logger.log(Level.SEVERE, e.getMessage(), e);
            return List.of();
        } catch (Exception e) {
            logger.severe("Error reading csv file");
            logger.log(Level.SEVERE, e.getMessage(), e);
            return List.of();
        }
    }
}
