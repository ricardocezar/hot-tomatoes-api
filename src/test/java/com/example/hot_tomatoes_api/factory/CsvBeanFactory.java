package com.example.hot_tomatoes_api.factory;

import com.example.hot_tomatoes_api.dataloader.CsvBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CsvBeanFactory {
    public static List<CsvBean> getValidCsvBeans(int count) {
        List<CsvBean> data = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            CsvBean bean = new CsvBean();
            bean.setYear(getRandomYear());
            bean.setTitle(UUID.randomUUID().toString());
            bean.setStudios("Studio");
            bean.setProducers("Producer");
            bean.setWinner(getRandomWinner());
            data.add(bean);
        }
        return data;
    }

    private static String getRandomYear() {
        return String.valueOf((int)(Math.random() * (2024 - 1990 + 1) + 1990));
    }

    private static String getRandomWinner() {
        return Math.random() > 0.5 ? "yes" : "";
    }
}
