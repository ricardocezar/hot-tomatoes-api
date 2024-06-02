package com.example.hot_tomatoes_api.api;

import com.example.hot_tomatoes_api.domain.DistinguishedProducersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/distinguished-producers")
@RequiredArgsConstructor
public class DistinguishedProducersController {
    private final DistinguishedProducersUseCase distinguishedProducersUseCase;

    @GetMapping(produces = "application/json")
    public ResponseEntity<DistinguishedProducersDto> getDistinguishedProducers() {
        var response = DistinguishedProducerMapper.mapDomainObjectToDto(distinguishedProducersUseCase.execute());
        if (response.getMin().isEmpty() && response.getMax().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
