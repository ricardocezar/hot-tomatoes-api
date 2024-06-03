package com.example.hot_tomatoes_api.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DistinguishedProducerDto implements Serializable {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;
}
