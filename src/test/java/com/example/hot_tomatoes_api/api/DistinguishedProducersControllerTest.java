package com.example.hot_tomatoes_api.api;

import com.example.hot_tomatoes_api.domain.distinguished_producers_usecase.DistinguishedProducersUseCase;
import com.example.hot_tomatoes_api.factory.MovieFactory;
import com.example.hot_tomatoes_api.repository.ProducerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes =
        {DistinguishedProducersController.class, DistinguishedProducersUseCase.class})
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@DisplayName("DistinguishedProducersController")
class DistinguishedProducersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProducerRepository producerRepository;
    @Autowired
    private DistinguishedProducersUseCase distinguishedProducersUseCase;

    @Test
    @DisplayName("should return no content when there are no distinguished producers")
    void whenThereAreNoDistinguishedProducers_thenReturnNoContent() throws Exception {
        when(producerRepository.findAllAwardedProducers()).thenReturn(List.of());
        var response = mockMvc
                .perform(get("/distinguished-producers"))
                .andReturn()
                .getResponse();
        assertEquals(204, response.getStatus());
        assertEquals("{}", response.getContentAsString());
    }

    @Test
    @DisplayName("should return 200 when there are distinguished producers")
    void whenThereAreDistinguishedProducers_thenReturn200() throws Exception {
        when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioOneMinAndOneMax());
        var response = mockMvc
                .perform(get("/distinguished-producers"))
                .andReturn()
                .getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("should match the response schema when there are distinguished producers")
    void whenThereAreDistinguishedProducers_thenReturnValidSchema() throws Exception {
        when(producerRepository.findAllAwardedProducers()).thenReturn(MovieFactory.scenarioOneMinAndOneMax());
        var response = mockMvc
                .perform(get("/distinguished-producers"))
                .andReturn()
                .getResponse();
        String jsonResponse = response.getContentAsString();
        JsonNode schemaNode = JsonLoader.fromPath("src/test/resources/schema/distinguished-producers-response.json");
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = factory.getJsonSchema(schemaNode);
        JsonNode responseNode = JsonLoader.fromString(jsonResponse);
        ProcessingReport report = schema.validate(responseNode);
        assertTrue(report.isSuccess(), "JSON response does not match the schema: " + report.toString());
    }
}
