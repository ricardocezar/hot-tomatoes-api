package com.example.hot_tomatoes_api.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DistinguishedProducersDto implements Serializable {
    private List<DistinguishedProducerDto> min;
    private List<DistinguishedProducerDto> max;

    public DistinguishedProducersDto(List<DistinguishedProducerDto> min, List<DistinguishedProducerDto> max) {
        this.min = min;
        this.max = max;
    }

    public DistinguishedProducersDto() {
        this.min = new ArrayList<>();
        this.max = new ArrayList<>();
    }
}
