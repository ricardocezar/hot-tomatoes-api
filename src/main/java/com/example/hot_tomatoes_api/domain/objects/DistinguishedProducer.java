package com.example.hot_tomatoes_api.domain.objects;

public record DistinguishedProducer(
        String producerName,
        int interval,
        Year firstAwardedMovie,
        Year lastAwardedMovie)
{ }
