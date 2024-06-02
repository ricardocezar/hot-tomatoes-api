package com.example.hot_tomatoes_api.api;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DistinguishedProducersDto {
    private List<DistinguishedProducerDto> min = new ArrayList<>();
    private List<DistinguishedProducerDto> max = new ArrayList<>();
}

record DistinguishedProducerDto (String producer, int interval, int previousWin, int followingWin) {}
