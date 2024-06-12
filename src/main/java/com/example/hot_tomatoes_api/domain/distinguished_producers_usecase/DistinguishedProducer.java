package com.example.hot_tomatoes_api.domain.distinguished_producers_usecase;

import com.example.hot_tomatoes_api.domain.objects.Year;

public record DistinguishedProducer(
        String producerName,
        int interval,
        Year firstAwardedMovie,
        Year lastAwardedMovie)
{ }
