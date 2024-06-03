package com.example.hot_tomatoes_api.api;

import com.example.hot_tomatoes_api.domain.DistinguishedProducersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

@RestController
@RequestMapping("/distinguished-producers")
@RequiredArgsConstructor
public class DistinguishedProducersController {
    private static final Logger logger = Logger.getLogger(DistinguishedProducersController.class.getName());
    private final DistinguishedProducersUseCase distinguishedProducersUseCase;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DistinguishedProducersDto> getDistinguishedProducers() {
        var response = DistinguishedProducerMapper.mapDomainObjectToDto(distinguishedProducersUseCase.execute());
        if (response.getMin().isEmpty() && response.getMax().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers()).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).headers(headers()).body(response);
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
