package com.example.hot_tomatoes_api.dataloader;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class CsvBean {
    @CsvBindByName
    private String year;
    @CsvBindByName
    private String title;
    @CsvBindByName
    private String studios;
    @CsvBindByName
    private String producers;
    @CsvBindByName
    private String winner;

    @Override
    public String toString() {
        return "{" +
                "year='" + year +
                ", title='" + title +
                ", studios='" + studios +
                ", producers='" + producers +
                ", winner='" + winner +
                '}';
    }
}
