package com.example.hot_tomatoes_api.dataloader;

import com.example.hot_tomatoes_api.domain.objects.Producer;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProducerDataTransformer {
    public Set<Producer> producerTransformer(String producersNames) {
        producersNames = producersNames.replace(" and ", ",");
        String[] producerNamesSet = producersNames.split(",");
        Set<Producer> producersSet = new HashSet<>();
        for (String producerName : producerNamesSet) {
            if (producerName.trim().isEmpty()) {
                continue;
            }
            Producer producer = new Producer();
            producer.setName(producerName.trim());
            producersSet.add(producer);
        }
        return producersSet;
    }
}
